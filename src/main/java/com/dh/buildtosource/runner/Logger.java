package com.dh.buildtosource.runner;

public class Logger {

  public static void print(Object... msg) {
    StringBuilder sb = new StringBuilder();
    for(Object o : msg) {
      if(sb.length()>0) {
        sb.append(" ");
      }
      sb.append(o.toString());
    }
    System.out.println(sb.toString());
  }

}
