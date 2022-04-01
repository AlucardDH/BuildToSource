package com.dh.buildtosource.build.model;

import com.dh.buildtosource.build.parser.Binary;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Sector extends AbstractData {

  private int firstWallIndex;
  private int wallCount;

  private SurfaceStats ceilingStats;
  private SurfaceStats floorStats;

  private int visibility;

  private int filler;

  public SurfaceStats getCeilingStats() {
    if(ceilingStats==null) {
      ceilingStats = new SurfaceStats();
    }
    return ceilingStats;
  }

  public SurfaceStats getFloorStats() {
    if(floorStats==null) {
      floorStats = new SurfaceStats();
    }
    return floorStats;
  }

  @Data
  public static class SurfaceStats {

    int Z;

    /*
    bit 0: 1 = parallaxing, 0 = not
    bit 1: 1 = sloped, 0 = not
    bit 2: 1 = swap x&y, 0 = not
    bit 3: 1 = double smooshiness
    bit 4: 1 = x-flip
    bit 5: 1 = y-flip
    bit 6: 1 = Align texture to first wall of sector
    bits 7-15: reserved
    */
    boolean parallaxing;
    boolean sloped;
    boolean swapXY;
    boolean doubleSmooshiness;
    boolean xFlip;
    boolean yFlip;
    boolean alignToFirstWall;


        /*
    INT16LE	ceilingpicnum	Ceiling texture (index into ART file)
    INT16LE	ceilingheinum	Slope value (rise/run; 0 = parallel to floor, 4096 = 45 degrees)
    INT8	ceilingshade	Shade offset
    UINT8	ceilingpal	Palette lookup table number (0 = standard colours)
    UINT8	ceilingxpanning	Texture coordinate X-offset for ceiling
    UINT8	ceilingypanning	Texture coordinate Y-offset for ceiling
     */

    int textureIndex;
    int slope;
    int shade;
    int palette;
    int xPanning;
    int yPanning;

    public void updateFlags(int data) {
      parallaxing = Binary.getBit(data,0);
      sloped = Binary.getBit(data,1);
      swapXY = Binary.getBit(data,2);
      doubleSmooshiness = Binary.getBit(data,3);
      xFlip = Binary.getBit(data,4);
      yFlip = Binary.getBit(data,5);
      alignToFirstWall = Binary.getBit(data,6);
    }
  }

}
