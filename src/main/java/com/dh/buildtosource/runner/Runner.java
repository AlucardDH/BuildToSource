package com.dh.buildtosource.runner;

import com.dh.buildtosource.build.model.BuildMap;
import com.dh.buildtosource.build.parser.BuildInputStream;
import org.apache.commons.lang3.time.StopWatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Runner {

  public static void main(String[] args) {
    // test map
    //https://www.arrovfnukem.com/en/50-square-andrew-spitler

    StopWatch stopWatch = StopWatch.createStarted();

    try(
        InputStream is = new FileInputStream(args[0]);
        BuildInputStream buildInputStream = new BuildInputStream(is);
    ) {
      BuildMap buildMap = buildInputStream.readMap();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    Logger.print("Done in",stopWatch.getTime(),"ms");

  }

}
