package com.wmx.quartz;

import com.wmx.common.MyTask;
import com.wmx.common.MailUtil;
import com.wmx.model.task.Task;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MailJob extends QuartzJobBean{

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //MailUtil.sendMail("1797078678@qq.com", "定时邮件发给你", "内容哈哈啊哈哈哈哈哈");
        //获取到任务定义中我们设置的参数（查询出的定时任务）
        Task t = (Task) jobExecutionContext.getMergedJobDataMap().get("myTask");
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getMergedJobDataMap().get("applicationContext");
        try {
            //利用反射执行对应方法
            //如果查出的数据中class（方法地址）包含了
            if (t.getTaskClass().contains("MyTask")) {
                Object obj = applicationContext.getBean("myTask");
                //得到任务的类名和方法名
                Method method = obj.getClass().getMethod(t.getTaskMethod());
                method.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
