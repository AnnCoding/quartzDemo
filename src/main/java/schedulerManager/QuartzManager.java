package schedulerManager;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

/**
 * @author chenjiena
 * @version 1.0
 * @created 2020/4/30.
 */
public class QuartzManager {

    private Scheduler scheduler;

    @SuppressWarnings({"unchecked","rawtypes"})
    public void addJob(String jobName,String jobGroupName,String triggerName,String triggerGroupName,Class jobClass,String cron){
        try {
//            1. 指定要运行的任务，设置任务名，任务组别
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();

//            2. 配置触发器
//            A 新建触发器构造类
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
//            B 设置触发器名   触发器组名
            triggerBuilder.withIdentity(triggerName,triggerGroupName);
            triggerBuilder.startNow();
//            C 设置触发器的时间规则
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
//            D 用触发器构造类创建trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

//            3. 把上面创建哈的任务和触发器注册到调度器
            scheduler.scheduleJob(jobDetail,trigger);

//            启动调度器
            if (!scheduler.isShutdown()){
                scheduler.start();
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    //修改定时任务的组名
    public void modifyJobTime(String jobName,String jobGroupName,String triggerName,String triggerGroupName,String cron){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            if (trigger == null){
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)){
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(triggerName,triggerGroupName);
                triggerBuilder.startNow();

                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                trigger = (CronTrigger) triggerBuilder.build();

                scheduler.rescheduleJob(triggerKey,trigger);
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //删除定时任务
    public void removeJob(String jobName,String jobGroupName,String triggerName,String triggerGroupName){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName,triggerGroupName);

            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(triggerName,triggerGroupName));

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //启动所有定时任务
    public void startJobs(){
        try {
            scheduler.start();
        }catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //关闭所有定时任务
    public void shutdownJobs(){
        try {
            if (!scheduler.isShutdown()){
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
