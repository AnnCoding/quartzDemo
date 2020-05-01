package schedulerManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author chenjiena
 * @version 1.0
 * @created 2020/5/1.
 */
public class FirstJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("第一个任务开始执行了！！！First job is running ..." + new Date());
    }
}
