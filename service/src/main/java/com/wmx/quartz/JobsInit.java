package com.wmx.quartz;

import com.wmx.model.task.Task;
import com.wmx.service.task.TaskService;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobsInit implements InitializingBean {

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        //项目启动时 休眠30秒以后再执行
        Thread.sleep(30000);
        //从数据库查询出所有的待执行定时任务
        Task task = new Task();
        task.setTaskFlag(1);
        List<Task> list = taskService.selectTaskList(task);
        //遍历集合，把任务挨个添加到调度器
        if (null != list) {
            list.forEach(t -> {
                quartzManager.addJob(t);
            });
        }
    }
}
