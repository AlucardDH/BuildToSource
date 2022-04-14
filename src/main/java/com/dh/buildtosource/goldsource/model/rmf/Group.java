package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class Group extends AbstactObject {

//    nstring        "CMapGroup"
//    int            visgroup
//    Color          display color (overrides color of any contained solids)
//    int            number of objects in group
    int objectsCount;
//    Object[]       objects (can contain solids, entities or nested groups!)
    AbstactObject[] objects;

    Group() {
        super("CMapGroup");
    }
}
