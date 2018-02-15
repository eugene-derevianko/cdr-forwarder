package com.symulakr.telegram.bot;

import com.symulakr.telegram.bot.message.CdrTranslator;
import com.symulakr.telegram.bot.model.Cdr;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@org.springframework.context.annotation.Configuration
public class Configuration {

   @Bean
   public RestTemplate restTemplate(){
      return new RestTemplate();
   }

   @Bean
   public Function<Cdr, String> translator(){
      return new CdrTranslator();
   }

}
