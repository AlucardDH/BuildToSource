package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class VisGroup {

//    string(128)     name of vis group
    String name;

//    Color           display color
    Color color;
//    byte            ?
    byte unknown1;

//    int             index of visgroup (other objects refer to visgroups with this number)
    int index;

//    byte            1=visible, 0=not visible
    byte visibility;

//    byte[3]         ?
    byte[] unknown2;

}
