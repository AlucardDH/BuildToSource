package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class Entity extends AbstactObject {

//    nstring        "CMapEntity"
//    int            visgroup
//    byte[3]        display color (VHE draws all entities purple regardless of this or any other color value)
//    int            number of brushes (0 for point entity)
    int brushesCount;
//    Solid[]        entity brushes
    Solid[] brushes;
//    nstring        classname (max length is 128)
    String classname;
//    byte[4]        ?
    byte[] unknown1;
//    int            entity flags
    int flags;
//    int            number of key/value pairs
    int keyValueCount;
//    nstring[]      entity key/value pairs (array contains key, value, key value, etc.. max length is 32 for keys and 100 for values)
    String[] keyValues;
//    byte[14]       ?
    byte[] unknown2;
//    Vector         position of entity in world coordinates (only used for point entities)
    Vector position;
//    byte[4]        ?
    byte[] unknown3;

    Entity() {
        super("CMapEntity");
    }
}
