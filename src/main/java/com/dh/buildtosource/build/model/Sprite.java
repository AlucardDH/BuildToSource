package com.dh.buildtosource.build.model;

import com.dh.buildtosource.build.parser.Binary;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Sprite extends AbstractData {

  private Coordinates3D coordinates;

  // bit 0: 1 = Blocking sprite (use with clipmove, getzrange)
  private boolean blockingType1;
  // bit 1: 1 = transluscence, 0 = normal
  private boolean transluscence;
  // bit 2: 1 = x-flipped, 0 = normal
  private boolean xFlipped;
  // bit 3: 1 = y-flipped, 0 = normal
  private boolean yFlipped;
  // bits 5-4: 00 = FACE sprite (default)
  // 01 = WALL sprite (like masked walls)
  // 10 = FLOOR sprite (parallel to ceilings&floors)
  private boolean orientationFace;
  private boolean orientationWall;
  private boolean orientationFloor;
  // bit 6: 1 = 1-sided sprite, 0 = normal
  private boolean oneSided;
  // bit 7: 1 = Real centered centering, 0 = foot center
  private boolean footCentered;
  // bit 8: 1 = Blocking sprite (use with hitscan / cliptype 1)
  private boolean blockingType2;
  // bit 9: 1 = Transluscence reversing, 0 = normal
  private boolean transluscenceReversing;
  // bits 10-14: reserved
  // bit 15: 1 = Invisible sprite, 0 = not invisible
  private boolean invisible;

  // INT16LE	picnum	Texture index into ART file
  private int textureIndex;
  // INT8	shade	Shade offset of wall
  private int shade;
  // UINT8	pal	Palette lookup table number (0 = standard colours)
  private int palette;
  // UINT8	clipdist	Size of the movement clipping square (face sprites only)
  private int clipDistance;
  // UINT8	filler
  private int filler;
  // UINT8	xrepeat	Change pixel size to stretch/shrink textures
  private int xRepeat;
  // UINT8	yrepeat
  private int yRepeat;
  // INT8	xoffset	Centre sprite animations
  private int xOffset;
  // INT8	yoffset
  private int yOffset;

  // INT16LE	sectnum	Current sector of sprite's position
  private int sectorIndex;
  // INT16LE	statnum	Current status of sprite (inactive, monster, bullet, etc.)
  private int status;
  // INT16LE	ang	Angle the sprite is facing
  private int angle;
  // INT16LE	owner	! ?
  private int owner;
  // INT16LE	xvel	! ?
  private int xVel;
  // INT16LE	yvel	! ?
  private int yVel;
  // INT16LE	zvel	! ?
  private int zVel;

  public void updateFlags(int data) {
    // bit 0: 1 = Blocking sprite (use with clipmove, getzrange)
    blockingType1 = Binary.getBit(data,0);
    // bit 1: 1 = transluscence, 0 = normal
    transluscence = Binary.getBit(data,1);
    // bit 2: 1 = x-flipped, 0 = normal
    xFlipped = Binary.getBit(data,2);
    // bit 3: 1 = y-flipped, 0 = normal
    yFlipped = Binary.getBit(data,3);

    // bits 5-4: 00 = FACE sprite (default)
    // 01 = WALL sprite (like masked walls)
    // 10 = FLOOR sprite (parallel to ceilings&floors)
    orientationFace = !Binary.getBit(data,5) && !Binary.getBit(data,4);
    orientationWall = !Binary.getBit(data,5) && Binary.getBit(data,4);
    orientationFloor = Binary.getBit(data,5) && !Binary.getBit(data,4);

    // bit 6: 1 = 1-sided sprite, 0 = normal
    oneSided = Binary.getBit(data,6);
    // bit 7: 1 = Real centered centering, 0 = foot center
    footCentered = !Binary.getBit(data,7);
    // bit 8: 1 = Blocking sprite (use with hitscan / cliptype 1)
    blockingType2 = Binary.getBit(data,8);
    // bit 9: 1 = Transluscence reversing, 0 = normal
    transluscenceReversing = Binary.getBit(data,9);
    // bits 10-14: reserved
    // bit 15: 1 = Invisible sprite, 0 = not invisible
    invisible = Binary.getBit(data,15);
  }
}
