package com.symulakr.telegram.bot;

import com.symulakr.telegram.bot.task.EveryTenSecondsJob;
import com.symulakr.telegram.bot.task.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;
import java.util.List;

@Configuration
@EnableScheduling
public class Schedule {

   private List<EveryTenSecondsJob> every10Seconds;

   @Autowired
   public Schedule(List<EveryTenSecondsJob> every10Seconds) {
      this.every10Seconds = every10Seconds;
   }


   @Scheduled(fixedDelay = 10000)
   public void every10Seconds() {
      execute(every10Seconds);
   }

   private void execute(Collection<? extends Job> jobs) {
      if (jobs != null) {
         jobs.forEach(Job::doIt);
      }
   }

}
