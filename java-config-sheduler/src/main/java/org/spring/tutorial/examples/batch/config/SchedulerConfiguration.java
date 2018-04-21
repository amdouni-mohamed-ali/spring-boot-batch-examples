package org.spring.tutorial.examples.batch.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Job processDataJob;

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

//            some examples from the spring documentation :
//            "0 0 * * * *" = the top of every hour of every day.
//            "*/10 * * * * *" = every ten seconds.
//            "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//            "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
//            "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
//            "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//            "0 0 0 25 12 ?" = every Christmas Day at midnight
//
//            cron is represented by six fields :
//            second, minute, hour, day of month, month, day(s) of week
//            * : every
//            */X : every X
//            ? : any value , not specific

    @Scheduled(cron = "*/5 * * * * *")    // job will be started every 5 seconds

//    @Scheduled(cron="5 30 6 * * 1-5")     // every working day (Monday-Friday) at 6 AM 30 minutes 5 seconds

//    @Scheduled(cron="*/5 28 9 21 4 ?")     // every 21 April at 9 AM 28 minutes.
    // every 5 seconds will be started for just one minute (). if we exceed the minute 28 the job will be stopped.
    //use the ? and not the * wildcard (if you want to start the job on a specific day use the ?)

//    @Scheduled(cron="0 0/1 9 21 4 SAT")  // 21 April at 9 AM every 1 minute -> if the day of the week is a saturday

//    @Scheduled(cron="0 0 0/1 21 * ?")   // 21 April every 1 hour
    public void perform() throws Exception {

        System.out.println("Starting job ...");

        JobParameters param = new JobParametersBuilder().addString("JobIdentifier",
                String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(processDataJob, param);

        System.out.println("Job finished with status :" + execution.getStatus());
    }
}
