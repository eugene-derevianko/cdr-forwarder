package com.symulakr.telegram.bot;

import java.util.function.Function;

public interface MarkdownTranslator<T, R> extends Function<T, R> {

   default String bold(String str){
      return "*" + str + "*";
   }

   default String italic(Object str){
      return "_" + str + "_";
   }

}
