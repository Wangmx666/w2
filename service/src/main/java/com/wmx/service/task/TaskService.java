package com.wmx.service.task;

import com.wmx.model.task.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {

    //查询定时任务
    List<Task> selectTaskList(Task task);

    //查询任务列表
    Map<String,Object> selectAllTaskList(Task task);

    //新增任务
    void insertTask(Task task);

    //修改任务
    void updateTask(Task task);

    //删除任务
    void deleteTask(Task task);
}
