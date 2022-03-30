package com.dh.buildtosource.build.parser;

import com.dh.buildtosource.build.model.AbstractData;
import com.dh.buildtosource.build.model.BuildMap;
import com.dh.buildtosource.build.model.Coordinates2D;
import com.dh.buildtosource.build.model.Coordinates3D;
import com.dh.buildtosource.build.model.Sector;
import com.dh.buildtosource.build.model.Sprite;
import com.dh.buildtosource.build.model.Wall;
import com.dh.buildtosource.runner.Logger;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

@AllArgsConstructor
public class BuildInputStream implements AutoCloseable {

  private InputStream is;

  private int readINT(int intLengthBytes) throws IOException {
    BitSet bitSet = readBitSet((intLengthBytes));
    int result = (int)bitSet.toLongArray()[0];
    //Logger.print("readINT",result);
    return result;
  }

  private int readUINT(int intLengthBytes) throws IOException {
    BitSet bitSet = readBitSet((intLengthBytes));
    int result = toUInt(bitSet);
    Logger.print("readUINT",result);
    return result;
  }

  public int readINT32LE() throws IOException {
    return readINT(4);
  }

  public int readINT16LE() throws IOException {
    return readINT(2);
  }

  public int readINT8() throws IOException {
    return readINT(1);
  }

  public int readUINT8() throws IOException {
    return readUINT(1);
  }

  public int readUINT16LE() throws IOException {
    return readUINT(2);
  }

  public BitSet readBitSet(int intLengthBytes) throws IOException {
    byte[] data = new byte[intLengthBytes];
    int readBytes = is.read(data);
    if(readBytes<intLengthBytes) throw new IOException("Can not read enougth bytes ("+intLengthBytes+")");
    return BitSet.valueOf(data);
  }

  private int toUInt(BitSet data) {
    data.set(data.size(),false);
    return (int)data.toLongArray()[0];
  }

  public BuildMap readMap() throws IOException {
    BuildMap result = new BuildMap();

    // INT32LE	mapversion	File format version number (latest in released games is 7, source ports use 8 and 9)
    result.setVersion(readINT32LE());
    //Logger.print("Map version",result.getVersion());

    // INT32LE	posx	Player start point, X coordinate
    // INT32LE	posy	Player start point, Y coordinate
    // INT32LE	posz	Player start point, Z coordinate
    result.setPlayerStartPosition(readCoordinates3D());
    //Logger.print("Player coordinates",result.getPlayerStartPosition());

    // INT16LE	ang	Player starting angle
    result.setPlayerStartAngle(readINT16LE());

    Logger.print(result);
    // INT16LE	cursectnum	Sector number containing the start point
    result.setPlayerStartSectorNumber(readINT16LE());

    // UINT16LE	numsectors	Number of sectors in the map
    result.setSectorsCount(readUINT16LE());
    for (int sectorNumber = 0; sectorNumber < result.getSectorsCount(); sectorNumber++) {
      // SECTOR[numsectors]	sector	Information about each sector
      result.getSectors()[sectorNumber] = readSector();
    }

    // UINT16LE	numwalls	Number of walls in the map
    result.setWallsCount(readUINT16LE());
    for (int wallNumber = 0; wallNumber < result.getSectorsCount(); wallNumber++) {
      // WALL[numwalls]	wall	Information about each wall
      result.getWalls()[wallNumber] = readWall();
    }

    // UINT16LE	numsprites	Number of sprites in the map
    result.setSpritesCount(readUINT16LE());
    for (int spriteNumber = 0; spriteNumber < result.getSectorsCount(); spriteNumber++) {
      // SPRITE[numsprites]	sprite	Information about each sprite
      result.getSprites()[spriteNumber] = readSprite();
    }

    return result;
  }

  public Coordinates3D readCoordinates3D() throws IOException {
    Coordinates3D result = new Coordinates3D();
    result.setX(readINT32LE());
    result.setY(readINT32LE());
    result.setZ(readINT32LE());
    return result;
  }

  public Coordinates2D readCoordinates2D() throws IOException {
    Coordinates2D result = new Coordinates2D();
    result.setX(readINT32LE());
    result.setY(readINT32LE());
    return result;
  }

  public AbstractData readTags(AbstractData inOutData) throws IOException {
    // INT16LE	lotag	Significance is game-specific (Triggers, etc.)
    // INT16LE	hitag	Significance is game-specific (Triggers, etc.)
    // INT16LE	extra	Significance is game-specific

    inOutData.setLotag(readINT16LE());
    inOutData.setHitag(readINT16LE());
    inOutData.setExtra(readINT16LE());
    
    return inOutData;
  }

  public Sector readSector() throws IOException {
    Sector result = new Sector();
    
    //INT16LE	wallptr	Index to first wall in sector ! Index in what units? count? offset?
    result.setFirstWallIndex(readINT16LE());
    //INT16LE	wallnum	Number of walls in sector
    result.setWallCount(readINT16LE());

    // INT32LE	ceilingz	Z-coordinate (height) of ceiling at first point of sector
    result.getCeilingStats().setZ(readINT32LE());
    // INT32LE	floorz	Z-coordinate (height) of floor at first point of sector
    result.getFloorStats().setZ(readINT32LE());

    // INT16LE	ceilingstat
    result.getCeilingStats().updateFlags(readINT16LE());
    // INT16LE	floorstat
    result.getFloorStats().updateFlags(readINT16LE());

    // INT16LE	ceilingpicnum	Ceiling texture (index into ART file)
    result.getCeilingStats().setTextureIndex(readINT16LE());
    // INT16LE	ceilingheinum	Slope value (rise/run; 0 = parallel to floor, 4096 = 45 degrees)
    result.getCeilingStats().setSlope(readINT16LE());
    // INT8	ceilingshade	Shade offset
    result.getCeilingStats().setShade(readINT8());
    // UINT8	ceilingpal	Palette lookup table number (0 = standard colours)
    result.getCeilingStats().setPalette(readUINT8());
    // UINT8	ceilingxpanning	Texture coordinate X-offset for ceiling
    result.getCeilingStats().setXPanning(readUINT8());
    // UINT8	ceilingypanning	Texture coordinate Y-offset for ceiling
    result.getCeilingStats().setYPanning(readUINT8());

    // INT16LE	floorpicnum	Floor texture (index into ART file)
    result.getFloorStats().setTextureIndex(readINT16LE());
    // INT16LE	floorheinum	Slope value (rise/run; 0 = parallel to floor, 4096 = 45 degrees)
    result.getFloorStats().setSlope(readINT16LE());
    // INT8	floorshade	Shade offset
    result.getFloorStats().setShade(readINT8());
    // UINT8	floorpal	Palette lookup table number (0 = standard colours)
    result.getFloorStats().setPalette(readUINT8());
    // UINT8	floorxpanning	Texture coordinate X-offset for floor
    result.getFloorStats().setXPanning(readUINT8());
    // UINT8	floorypanning	Texture coordinate Y-offset for floor
    result.getFloorStats().setYPanning(readUINT8());

    // UINT8	visibility	How fast an area changes shade relative to distance
    result.setVisibility(readUINT8());

    // UINT8	filler	Padding byte
    result.setFiller(readUINT8());

    // INT16LE	lotag	Significance is game-specific (Triggers, etc.)
    // INT16LE	hitag	Significance is game-specific (Triggers, etc.)
    // INT16LE	extra	Significance is game-specific
    readTags(result);
    
    return result;
  }

  public Wall readWall() throws IOException {
    Wall result = new Wall();

    // INT32LE	x	X-coordinate of left side of wall (right side coordinate is obtained from the next wall's left side)
    // INT32LE	y	Y-coordinate of left side of wall (right side coordinate is obtained from the next wall's left side)
    result.setFirstPoint(readCoordinates2D());

    // INT16LE	point2	Index to next wall on the right (always in the same sector)
    result.setNextPoint(readINT16LE());
    // INT16LE	nextwall	Index to wall on other side of wall (-1 if there is no sector there)
    result.setNextWall(readINT16LE());
    // INT16LE	nextsector	Index to sector on other side of wall (-1 if there is no sector)
    result.setNextSector(readINT16LE());

    // INT16LE	cstat
    // bit 0: 1 = Blocking wall (use with clipmove, getzrange)
    // bit 1: 1 = bottoms of invisible walls swapped, 0 = not
    // bit 2: 1 = align picture on bottom (for doors), 0 = top
    // bit 3: 1 = x-flipped, 0 = normal
    // bit 4: 1 = masking wall, 0 = not
    // bit 5: 1 = 1-way wall, 0 = not
    // bit 6: 1 = Blocking wall (use with hitscan / cliptype 1)
    // bit 7: 1 = Transluscence, 0 = not
    // bit 8: 1 = y-flipped, 0 = normal
    // bit 9: 1 = Transluscence reversing, 0 = normal
    // bits 10-15: reserved
    result.updateFlags(readINT16LE());

    // INT16LE	picnum	Texture index into ART file
    result.setTextureIndex(readINT16LE());
    // INT16LE	overpicnum	Texture index into ART file for masked/one-way walls
    result.setTextureOverIndex(readINT16LE());
    // INT8	shade	Shade offset of wall
    result.setShade(readINT8());
    // UINT8	pal	Palette lookup table number (0 = standard colours)
    result.setPalette(readUINT8());
    // UINT8	xrepeat	Change pixel size to stretch/shrink textures
    result.setXRepeat(readUINT8());
    // UINT8	yrepeat
    result.setYRepeat(readUINT8());
    // UINT8	xpanning	Offset for aligning textures
    result.setXPanning(readUINT8());
    // UINT8	ypanning
    result.setYPanning(readUINT8());

    // INT16LE	lotag	Significance is game-specific (Triggers, etc.)
    // INT16LE	hitag	Significance is game-specific (Triggers, etc.)
    // INT16LE	extra	Significance is game-specific
    readTags(result);

    return result;
  }

  public Sprite readSprite() throws IOException {
    Sprite result = new Sprite();

    // INT32LE	x	X-coordinate of sprite
    // INT32LE	y	Y-coordinate of sprite
    // INT32LE	z	Z-coordinate of sprite
    result.setCoordinates(readCoordinates3D());


    // INT16LE	cstat
    // bit 0: 1 = Blocking sprite (use with clipmove, getzrange)
    // bit 1: 1 = transluscence, 0 = normal
    // bit 2: 1 = x-flipped, 0 = normal
    // bit 3: 1 = y-flipped, 0 = normal
    // bits 5-4: 00 = FACE sprite (default)
    // 01 = WALL sprite (like masked walls)
    // 10 = FLOOR sprite (parallel to ceilings&floors)
    // bit 6: 1 = 1-sided sprite, 0 = normal
    // bit 7: 1 = Real centered centering, 0 = foot center
    // bit 8: 1 = Blocking sprite (use with hitscan / cliptype 1)
    // bit 9: 1 = Transluscence reversing, 0 = normal
    // bits 10-14: reserved
    // bit 15: 1 = Invisible sprite, 0 = not invisible
    result.updateFlags(readINT16LE());

    // INT16LE	picnum	Texture index into ART file
    result.setTextureIndex(readINT16LE());
    // INT8	shade	Shade offset of wall
    result.setShade(readINT8());
    // UINT8	pal	Palette lookup table number (0 = standard colours)
    result.setPalette(readUINT8());
    // UINT8	clipdist	Size of the movement clipping square (face sprites only)
    result.setClipDistance(readUINT8());
    // UINT8	filler
    result.setFiller(readUINT8());
    // UINT8	xrepeat	Change pixel size to stretch/shrink textures
    result.setXRepeat(readUINT8());
    // UINT8	yrepeat
    result.setYRepeat(readUINT8());
    // INT8	xoffset	Centre sprite animations
    result.setXOffset(readINT8());
    // INT8	yoffset
    result.setYOffset(readINT8());
    
    // INT16LE	sectnum	Current sector of sprite's position
    result.setSectorIndex(readINT16LE());
    // INT16LE	statnum	Current status of sprite (inactive, monster, bullet, etc.)
    result.setStatus(readINT16LE());
    // INT16LE	ang	Angle the sprite is facing
    result.setAngle(readINT16LE());
    // INT16LE	owner	! ?
    result.setOwner(readINT16LE());
    // INT16LE	xvel	! ?
    result.setXVel(readINT16LE());
    // INT16LE	yvel	! ?
    result.setYVel(readINT16LE());
    // INT16LE	zvel	! ?
    result.setZVel(readINT16LE());

    // INT16LE	lotag	Significance is game-specific (Triggers, etc.)
    // INT16LE	hitag	Significance is game-specific (Triggers, etc.)
    // INT16LE	extra	Significance is game-specific
    readTags(result);
    
    return result;
  }

  @Override
  public void close() {
    try {
      is.close();
    } catch (Exception e) {
      // nothing more to close
    }
  }
}
