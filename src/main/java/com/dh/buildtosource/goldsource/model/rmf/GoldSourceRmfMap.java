package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class GoldSourceRmfMap {

//    float          version (usually 2.2): CD CC 0C 40 52 4D 46 ("????RMF")
float version;

    //    string(4)      "RMF"
    String format;
//    int            number of vis groups
    int visGroupsCount;

//    VisGroup[]     visgroups
    VisGroup[] visGroups;
//    nstring        "CMapWorld"
    String unknown1;
//    byte[7]        ? (probably visgroup and Color fields but not used by VHE)
    byte[] unknown2;

//    int            number of objects
    int objectsCount;
//    Object[]       world brushes, entities and groups
    AbstactObject[] objects;

//    nstring        classname of worldspawn entity (should be "worldspawn")
    String worldSpawnEntityClassname;

//    byte[4]        ?
    byte[] unknown3;

//    int            worldspawn entity flags (VHE does not allow you to set any flags for the worldspawn so these are probably unused)
    int worldSpawnEntityFlags;

//    int            number of worldspawn entity key/value pairs
    int worldSpawnEntityKeyValuesCount;

//    nstring[]      worldspawn key/value pairs (standard keys are "classname"="worldspawn", "sounds"="#", "MaxRange"="#", "mapversion"="220")
    String[] worldSpawnEntityKeyValues;

//    byte[12]       ?
    byte[] unknown4;

//    int            number of paths
    int pathsCount;

//    Path[]         paths
    Path[] paths;

//    string(8)      "DOCINFO"
    String docInfo;

//    float          appears to be a version number for camera data
    float cameraVersion;

//    int            active camera index
    int activeCamera;
//    int            number of cameras
    int camerasCount;
//    Camera[]       cameras
    Camera[] cameras;

}
