package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class Solid extends AbstactObject {

//    nstring        "CMapSolid"
//    int            visgroup
//    Color          display color (VHE ignores this if the solid is part of an entity or group)
//    byte[4]        ?
    byte[] unknown1;
//    int            number of faces
    int facesCount;
//    Face[]         faces in no particular order
    Face[] faces;

    Solid() {
        //    nstring        "CMapSolid"
        super("CMapSolid");
    }



}
