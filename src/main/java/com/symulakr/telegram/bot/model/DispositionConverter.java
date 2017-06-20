package com.symulakr.telegram.bot.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DispositionConverter implements AttributeConverter<Disposition, String> {

   @Override
   public String convertToDatabaseColumn(Disposition attribute){
      return attribute.toString();
   }

   @Override
   public Disposition convertToEntityAttribute(String dbData){
      switch (dbData) {
         case "ANSWERED":
            return Disposition.ANSWERED;
         case "BUSY":
            return Disposition.BUSY;
         case "FAILED":
            return Disposition.FAILED;
         case "NO ANSWER":
            return Disposition.NO_ANSWER;
         case "CONGESTION":
            return Disposition.CONGESTION;
         default:
            throw new IllegalArgumentException("Unknown" + dbData);
      }
   }
}