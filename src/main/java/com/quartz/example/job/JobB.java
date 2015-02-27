package com.quartz.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobB implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		System.out.println("Execute JobB ....");
		
	}

}