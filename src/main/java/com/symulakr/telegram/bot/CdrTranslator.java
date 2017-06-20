package com.symulakr.telegram.bot;

import com.symulakr.telegram.bot.model.Cdr;

import java.text.SimpleDateFormat;

public class CdrTranslator implements MarkdownTranslator<Cdr, String> {

   private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy 'at' h:mm a");

   @Override
   public String apply(Cdr cdr){
      StringBuilder sb = new StringBuilder();
      sb.append(bold("Date: "))
            .append(dateFormat.format(cdr.getCalldate()))
            .append('\n')
            .append(bold("From: "))
            .append(cdr.getClid())
            .append('\n')
            .append(bold("To: "))
            .append(cdr.getDst())
            .append('\n')
            .append(bold("Status: "))
            .append(cdr.getDisposition())
            .append('\n')
            .append(bold("Duration: "))
            .append(italic(convertDuration(cdr.getDuration())))
            .append('\n');
      return sb.toString();
   }

   private String convertDuration(float duration){
      return (int) (duration / 60) + "m " + (int) (duration % 60) + "s";
   }

}
