package com.symulakr.telegram.bot;

import com.symulakr.telegram.bot.message.MarkdownMessageBuilder;
import com.symulakr.telegram.bot.model.Cdr;
import com.symulakr.telegram.bot.task.EveryTenSecondsJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Service
public class LastCdrForwarder implements EveryTenSecondsJob {

   @Value("${telegram.bot.token}")
   private String token;

   @Value("${telegram.chat.id}")
   private String chatId;

   private final CdrRepository repository;

   private final RestTemplate restTemplate;

   private final Function<Cdr, String> translator;

   private int lastId = 0;
   private boolean connected = false;

   @Autowired
   public LastCdrForwarder(CdrRepository repository,
                           RestTemplate restTemplate,
                           Function<Cdr, String> translator) {
      this.repository = repository;
      this.restTemplate = restTemplate;
      this.translator = translator;
   }

   @Override
   public void doIt() {
      if (connected) {
         forwardLastCdr();
      } else {
         waitConnection();
      }
   }

   private void forwardLastCdr() {
      try {
         Cdr last = repository.findFirstByOrderByIdDesc();
         if (lastId < last.getId()) {
            sendMessage(translator.apply(last));
            lastId = last.getId();
         }
      } catch (NestedRuntimeException e) {
         waitConnection();
      }
   }

   private void waitConnection() {
      try {
         sendMessage(MarkdownMessageBuilder.builder()
             .italic("Connected to database.")
             .lineFeed()
             .append("Found ")
             .bold(repository.count())
             .append(" records.")
             .build());
         connected = true;
      } catch (NestedRuntimeException e) {
         if (connected) {
            sendMessage(MarkdownMessageBuilder.builder()
                .bold("Can't connect to database.")
                .build());
            }
         connected = false;
      }
   }

   private void sendMessage(String message) {
      restTemplate.getForObject("https://api.telegram.org/bot{token}/sendMessage?chat_id={chatId}&text={text}&parse_mode=Markdown", String.class, token, chatId, message);
   }
}
