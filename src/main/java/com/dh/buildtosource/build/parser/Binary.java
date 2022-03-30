package com.dh.buildtosource.build.parser;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Binary {

  public static boolean getBit(byte[] data,int bitIndex) {
    int dataIndex = data.length-1-bitIndex/8;
    int dataBitIndex =  bitIndex%8;
    return (data[dataIndex]>>dataBitIndex & 1)==1;
  }

  public static boolean getBit(int data,int bitIndex) {
    return (data>>bitIndex & 1)==1;
  }

}
