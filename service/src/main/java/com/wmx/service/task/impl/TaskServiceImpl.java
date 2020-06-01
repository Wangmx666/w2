package com.wmx.service.task.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wmx.mapper.task.TaskMapper;
import com.wmx.model.task.Task;
import com.wmx.quartz.QuartzManager;
import com.wmx.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private QuartzManager quartzManager;

    //查询定时任务
    @Override
    public List<Task> selectTaskList(Task task) {
        List<Task> list = taskMapper.selectTaskList(task);
        return list;
    }

    //查询任务列表
    @Override
    public Map<String, Object> selectAllTaskList(Task task) {
        Map<String, Object> map = new HashMap<>();
        //分页
        Page<Object> page = PageHelper.startPage(task.getPage(), task.getLimit());
        //查询
        List<Task> list = selectTaskList(task);
        //获取总条数
        long total = page.getTotal();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", total);
        return map;
    }

    //新增任务
    @Override
    public void insertTask(Task task) {
        if(1 == task.getTaskFlag()) {
            //立即执行，把任务添加到定时器触发器中
            quartzManager.addJob(task);
        }
        //把信息保存到数据库中
        taskMapper.insertTask(task);
    }

    //修改任务
    @Override
    public void updateTask(Task task) {
        if(task.getBtn().equals("stop")){
            //用户点击了停止任务，给Flag赋予0存到数据库
            task.setTaskFlag(0);
        }
        if(task.getBtn().equals("start")){
            //用户点击了开始任务，给Flag赋予1存到数据库
            task.setTaskFlag(1);
        }
        //调用自定义封装类（走调度器增删方法）
        updateTaskDetail(task);
    }


    //自定义封装的方法（修改调度器）
    private void updateTaskDetail(Task task) {
        //修改数据库
        taskMapper.updateTask(task);
        //把任务信息从数据库查询出来(不需要用flag作为条件所以改-1)
        task.setTaskFlag(-1);
        List<Task> list = taskMapper.selectTaskList(task);
        if (null != list && 1 == list.size()) {
            //取出业务字段 判断 如果是停止，走调度器remove方法
            if(task.getBtn().equals("stop")) {
                quartzManager.removeJob(list.get(0));
            } else {
                //如果是开始，走调度器add方法
                quartzManager.addJob(list.get(0));
            }
        }
    }

    //删除任务
    @Override
    public void deleteTask(Task task) {
        if(1 == task.getTaskFlag()){
            task.setBtn("stop");
            //查询并停止这个任务
            updateTaskDetail(task);
        }
        //删除数据库中的任务信息
        taskMapper.deleteTask(task);
    }
}
