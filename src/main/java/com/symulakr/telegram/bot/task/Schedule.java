package com.symulakr.telegram.bot.task;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class Schedule {

   private final List<EveryTenSecondsJob> every10Seconds;

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
