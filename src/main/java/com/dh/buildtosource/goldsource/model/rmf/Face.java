package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class Face {

//    string(256)     texture name
    String textureName;

//    float           ?
    float unknown1;
//    Vector          texture U axis
    Vector uAxis;
//    float           texture X shift
    float xShift;
//    Vector          texture V axis
    Vector vAxis;
//    float           texture Y shift
    float yShift;
//    float           texture rotation in degrees
    float rotation;
//    float           texture X scale factor
    float xScale;
//    float           texture Y scale factor
    float yScale;
//    byte[16]        ?
    byte[] unknown2;
//    int             number of vertices
    int verticesCount;
//    Vector[]        vertex coordinates in clockwise order looking from front of face
    Vector[] vertices;
//    Vector[3]       3 points defining plane of face (VHE simply uses a copy of the first 3 vertices)
    Vector[] planeVertices;
}
