package com.symulakr.telegram.bot;

import com.symulakr.telegram.bot.model.Cdr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Configuration
@EnableScheduling
public class Schedule {

   @Value("${telegram.bot.token}")
   private String token;

   @Value("${telegram.chat.id}")
   private String chatId;

   @Autowired
   private CdrRepository repository;

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private Function<Cdr, String> translator;

   int lastId = 0;

   @Scheduled(fixedDelay = 10000)
   public void execute() {
      Cdr last = repository.findFirstByOrderByIdDesc();
      if (lastId < last.getId()) {
         restTemplate.getForObject("https://api.telegram.org/bot{token}/sendMessage?chat_id={chatId}&text={text}&parse_mode=Markdown", String.class, token, chatId, translator.apply(last));
      }
      lastId = last.getId();
   }

}
