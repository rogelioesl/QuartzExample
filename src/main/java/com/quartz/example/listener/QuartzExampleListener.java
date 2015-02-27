package com.quartz.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import com.quartz.example.job.JobA;
import com.quartz.example.job.JobB;

public class QuartzExampleListener extends QuartzInitializerListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void contextInitialized(ServletContextEvent pScl) {
		super.contextInitialized(pScl);
		
		ServletContext ctx = pScl.getServletContext();
		StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
		
		try {
			Scheduler scheduler = factory.getScheduler();
			
			// Calcular las expresiones en http://www.cronmaker.com/
			
            JobDetail jobA = JobBuilder.newJob(JobA.class).build();
            Trigger triggerA = TriggerBuilder.newTrigger().withIdentity("JobA").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")
            ).startNow().build();
            scheduler.scheduleJob(jobA, triggerA);
            
            
            JobDetail jobB = JobBuilder.newJob(JobB.class).build();
            Trigger triggerB = TriggerBuilder.newTrigger().withIdentity("JobB").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")
            ).startNow().build();
            scheduler.scheduleJob(jobB, triggerB);
            
            // empiezan los jobs
            scheduler.start();
		} catch (Exception ex) {
			
		}
	}
}