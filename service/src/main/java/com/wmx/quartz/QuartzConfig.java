package com.wmx.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {


//    /**
//     * 调度器工厂
//     * */
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setOverwriteExistingJobs(true);//覆盖已存在的任务
//        schedulerFactoryBean.setStartupDelay(60);//延时60秒启动定时任务，避免系统未完全启动却开始执行定时任务的情况
//        return schedulerFactoryBean;
//    }
//
//    // 创建schedule
//    @Bean
//    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
//        return schedulerFactoryBean.getScheduler();
//    }


    // 定义定时器执行 我的邮件任务类
//    @Bean
//    public JobDetail helloJobDetail() {
//        return JobBuilder.newJob(MailJob.class).withIdentity("我的邮件任务").withDescription("任务的描述").storeDurably().build();
//    }

//    /**
//     * 能设置cron表达式
//     * */
//    @Bean
//    public CronTrigger sayHelloByCronTrigger(JobDetail jobDetail){
//        // Cron类型：通过cron表达式设置触发规则
//        CronScheduleBuilder csb = CronScheduleBuilder.cronSchedule(String.format("*/10 * * * * ?"))
//                .withMisfireHandlingInstructionDoNothing();
//
//        // 一个Trigger只对应一个Job，Schedule调度器调度Trigger执行对应的Job
//        CronTrigger cTrigger = TriggerBuilder.newTrigger().forJob(jobDetail).
//                withIdentity("触发器的名称").withDescription("触发器的描述").withSchedule(csb).startNow().build();
//        return cTrigger;
//    }


    /**
     * Simple触发器定义与设置
     * 不能设置cron表达式
     * 只能间隔执行
     * */
//    @Bean
//    public SimpleTrigger sayHelloBySimpleTrigger(JobDetail jobDetail ){
//        // Simple类型：可设置时间间隔、是否重复、触发频率（misfire机制）等
//        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(5).repeatForever();
//
//        // 一个Trigger只对应一个Job，Schedule调度器调度Trigger执行对应的Job
//        SimpleTrigger sTrigger = TriggerBuilder.newTrigger().forJob(jobDetail).
//                withIdentity("触发器的名称").withDescription("触发器的描述").withSchedule(ssb).build();
//        return sTrigger;
//    }

}
