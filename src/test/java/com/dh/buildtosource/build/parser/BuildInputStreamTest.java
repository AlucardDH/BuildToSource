package com.dh.buildtosource.build.parser;

import org.junit.Test;

public class BuildInputStreamTest {

    @Test
    public void bytesToBinary() {
        byte[] data = new byte[]{7,2};
        String result = BuildInputStream.bytesToBinaryLE(data);

    }
}