package com.symulakr.telegram.bot.model;

public enum Disposition {

   ANSWERED("ANSWERED"),
   BUSY("BUSY"),
   FAILED("FAILED"),
   NO_ANSWER("NO ANSWER"),
   CONGESTION("CONGESTION");

   private final String DB_VALUE;

   Disposition(String dbValue){
      this.DB_VALUE = dbValue;
   }

   @Override
   public String toString(){
      return DB_VALUE;
   }
}
