package schedulerManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenjiena
 * @version 1.0
 * @created 2020/5/1.
 */
public class JobTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:quartz-manager-context.xml");
        QuartzManager manager = (QuartzManager) context.getBean("quartzManager");

        try {
            System.out.println("【增加启动First job 】开始（每一秒输出一次）...");
            manager.addJob(
                    "firstJobDetail",
                    "jobGroupOne",
                    "cronTrigger",
                    "triggerGroupOne",
                    FirstJob.class,
                    "0/1 * * * * ?"
            );

            Thread.sleep(5000);
            System.out.println("【修改First job】开始（每2秒输出一次）...");
            manager.modifyJobTime(
                    "firstJobDetail",
                    "jobGroupOne",
                    "cronTrigger",
                    "triggerGroupOne",
                    "0/2 * * * * ?"
            );

            Thread.sleep(5000);
            System.out.println("【移除First job】...");
            manager.removeJob(
                    "firstJobDetail",
                    "jobGroupOne",
                    "cronTrigger",
                    "triggerGroupOne"
            );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






}
