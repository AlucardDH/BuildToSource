package com.dh.buildtosource.build.model;

import com.dh.buildtosource.build.parser.Binary;
import lombok.Data;

@Data
public class Wall extends AbstractData {

  // INT32LE	x	X-coordinate of left side of wall (right side coordinate is obtained from the next wall's left side)
  // INT32LE	y	Y-coordinate of left side of wall (right side coordinate is obtained from the next wall's left side)
  private Coordinates2D firstPoint;

  // INT16LE	point2	Index to next wall on the right (always in the same sector)
  private int nextPoint;

  // INT16LE	nextwall	Index to wall on other side of wall (-1 if there is no sector there)
  private int nextWall;

  // INT16LE	nextsector	Index to sector on other side of wall (-1 if there is no sector)
  private int nextSector;

  // INT16LE	cstat
  // bit 0: 1 = Blocking wall (use with clipmove, getzrange)
  private boolean blockingType1;
  // bit 1: 1 = bottoms of invisible walls swapped, 0 = not
  private boolean bottomsOfInvisibleWallsSwapped;
  // bit 2: 1 = align picture on bottom (for doors), 0 = top
  private boolean alignOnBottom;
  // bit 3: 1 = x-flipped, 0 = normal
  private boolean xFlipped;
  // bit 4: 1 = masking wall, 0 = not
  private boolean masking;
  // bit 5: 1 = 1-way wall, 0 = not
  private boolean oneWay;
  // bit 6: 1 = Blocking wall (use with hitscan / cliptype 1)
  private boolean blockingType2;
  // bit 7: 1 = Transluscence, 0 = not
  private boolean transluscence;
  // bit 8: 1 = y-flipped, 0 = normal
  private boolean yFlipped;
  // bit 9: 1 = Transluscence reversing, 0 = normal
  private boolean transluscenceReversing;
  // bits 10-15: reserved

  // INT16LE	picnum	Texture index into ART file
  private int textureIndex;

  // INT16LE	overpicnum	Texture index into ART file for masked/one-way walls
  private int textureOverIndex;

  // INT8	shade	Shade offset of wall
  private int shade;

  // UINT8	pal	Palette lookup table number (0 = standard colours)
  private int palette;

  // UINT8	xrepeat	Change pixel size to stretch/shrink textures
  private int xRepeat;

  // UINT8	yrepeat
  private int yRepeat;

  // UINT8	xpanning	Offset for aligning textures
  private int xPanning;

  // UINT8	ypanning
  private int yPanning;

  public void updateFlags(int data) {
    // bit 0: 1 = Blocking wall (use with clipmove, getzrange)
    blockingType1 = Binary.getBit(data,0);
    // bit 1: 1 = bottoms of invisible walls swapped, 0 = not
    bottomsOfInvisibleWallsSwapped = Binary.getBit(data,1);
    // bit 2: 1 = align picture on bottom (for doors), 0 = top
    alignOnBottom = Binary.getBit(data,2);
    // bit 3: 1 = x-flipped, 0 = normal
    xFlipped = Binary.getBit(data,3);
    // bit 4: 1 = masking wall, 0 = not
    masking = Binary.getBit(data,4);
    // bit 5: 1 = 1-way wall, 0 = not
    oneWay = Binary.getBit(data,5);
    // bit 6: 1 = Blocking wall (use with hitscan / cliptype 1)
    blockingType2 = Binary.getBit(data,6);
    // bit 7: 1 = Transluscence, 0 = not
    transluscence = Binary.getBit(data,7);
    // bit 8: 1 = y-flipped, 0 = normal
    yFlipped = Binary.getBit(data,8);
    // bit 9: 1 = Transluscence reversing, 0 = normal
    transluscenceReversing = Binary.getBit(data,9);
    // bits 10-15: reserved
  }


}
