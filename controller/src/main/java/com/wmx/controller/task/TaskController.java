package com.wmx.controller.task;

import com.wmx.model.task.Task;
import com.wmx.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    //跳转list页面
    @RequestMapping("/task/toList")
    String toTaskListPage() {
        return "task/list";
    }

    //查询任务列表
    @RequestMapping("/task/select")
    @ResponseBody
    Map<String, Object> selectAllTaskList(Task task) {
        Map<String, Object> map = taskService.selectAllTaskList(task);
        return map;
    }

    //跳转新增页面
    @RequestMapping("/task/toAdd")
    String toAdd() {
        return "task/add";
    }

    //新增任务
    @RequestMapping("task/insert")
    @ResponseBody
    String insertTask(Task task) {
        taskService.insertTask(task);
    return "{}";
    }

    //修改任务
    @RequestMapping("task/update")
    @ResponseBody
    String updateTask(Task task) {
        taskService.updateTask(task);
        return "{}";
    }

    //删除任务
    @RequestMapping("task/deleteTask")
    @ResponseBody
    String deleteTask(Task task) {
        taskService.deleteTask(task);
    return "{}";
    }
}
