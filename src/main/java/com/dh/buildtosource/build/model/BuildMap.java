package com.dh.buildtosource.build.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * INT32LE	mapversion	File format version number (latest in released games is 7, source ports use 8 and 9)
 * INT32LE	posx	Player start point, X coordinate
 * INT32LE	posy	Player start point, Y coordinate
 * INT32LE	posz	Player start point, Z coordinate
 * INT16LE	ang	Player starting angle
 * INT16LE	cursectnum	Sector number containing the start point
 * UINT16LE	numsectors	Number of sectors in the map
 * SECTOR[numsectors]	sector	Information about each sector
 * UINT16LE	numwalls	Number of walls in the map
 * WALL[numwalls]	wall	Information about each wall
 * UINT16LE	numsprites	Number of sprites in the map
 * SPRITE[numsprites]	sprite	Information about each sprite
 */
@NoArgsConstructor
@Data
@ToString
public class BuildMap {

  /**
   * INT32LE	mapversion	File format version number (latest in released games is 7, source ports use 8 and 9)
   */
  private int version;

  /**
   *  INT32LE	posx	Player start point, X coordinate
   *  INT32LE	posy	Player start point, Y coordinate
   *  INT32LE	posz	Player start point, Z coordinate
   */
  private Coordinates3D playerStartPosition;

  /**
   * INT16LE	ang	Player starting angle
   */
  private int playerStartAngle;

  /**
   * INT16LE	cursectnum	Sector number containing the start point
   */
  private int playerStartSectorNumber;

  /**
   * UINT16LE	numsectors	Number of sectors in the map
   */
  private int sectorsCount;

  /**
   * SECTOR[numsectors]	sector	Information about each sector
   */
  private Sector[] sectors;

  /**
   * UINT16LE	numwalls	Number of walls in the map
   */
  private int wallsCount;

  /**
   * WALL[numwalls]	wall	Information about each wall
   */
  public Wall[] walls;

  /**
   * UINT16LE	numsprites	Number of sprites in the map
   */
  private int spritesCount;

  /**
   * SPRITE[numsprites]	sprite	Information about each sprite
   */
  private Sprite[] sprites;

  public void setSectorsCount(int sectorsCount) {
    this.sectorsCount = sectorsCount;
    this.sectors = new Sector[sectorsCount];
  }

  public void setWallsCount(int wallsCount) {
    this.wallsCount = wallsCount;
    this.walls = new Wall[wallsCount];
  }

  public void setSpritesCount(int spritesCount) {
    this.spritesCount = spritesCount;
    this.sprites = new Sprite[spritesCount];
  }
}
