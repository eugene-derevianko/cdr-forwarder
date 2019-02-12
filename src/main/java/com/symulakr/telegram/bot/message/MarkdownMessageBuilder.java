package com.symulakr.telegram.bot.message;

public class MarkdownMessageBuilder {

   private final StringBuilder sb;

   private MarkdownMessageBuilder() {
      this.sb = new StringBuilder();
   }

   public String build() {
      return sb.toString();
   }

   public MarkdownMessageBuilder append(Object obj) {
      sb.append(obj);
      return this;
   }

   public MarkdownMessageBuilder lineFeed() {
      sb.append('\n');
      return this;
   }

   public MarkdownMessageBuilder bold(Object obj) {
      sb.append('*')
          .append(obj)
          .append('*');
      return this;
   }

   public MarkdownMessageBuilder italic(Object obj) {
      sb.append('_')
          .append(obj)
          .append('_');
      return this;
   }

   public static MarkdownMessageBuilder builder(){
      return new MarkdownMessageBuilder();
   }

}
