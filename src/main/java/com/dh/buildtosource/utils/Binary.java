package com.dh.buildtosource.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Binary {

  public static boolean getBit(int data,int bitIndex) {
    return (data>>bitIndex & 1)==1;
  }

}
