package com.symulakr.telegram.bot.message;

import com.symulakr.telegram.bot.model.Cdr;

import java.text.SimpleDateFormat;
import java.util.function.Function;

public class CdrTranslator implements Function<Cdr, String> {

   private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy 'at' h:mm a");

   @Override
   public String apply(Cdr cdr) {
      return MarkdownMessageBuilder.builder()
          .bold("Date: ")
          .append(dateFormat.format(cdr.getCalldate()))
          .lineFeed()
          .bold("From: ")
          .append(cdr.getClid())
          .lineFeed()
          .bold("To: ")
          .append(cdr.getDst())
          .lineFeed()
          .bold("Status: ")
          .append(cdr.getDisposition())
          .lineFeed()
          .bold("Duration: ")
          .italic(convertDuration(cdr.getDuration()))
          .lineFeed().build();
   }

   private String convertDuration(float duration) {
      return (int) (duration / 60) + "m " + (int) (duration % 60) + "s";
   }

}
