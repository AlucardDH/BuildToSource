package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class Corner {

//    Vector         position in world coordinates
    Vector position;
//    int            index for this corner.. used to generate targetnames (corner01, corner02, etc..)
    int index;
//    string(128)    name override (empty string if not overridden)
    String nameOverride;
//    int            number of key/value pairs
    int keyValueCount;
//    nstring[]      key/value pairs (array contains key, value, key value, etc.)
    String[] keyValues;
//    NOTE: these are supposed to be entity properties for the corner but VHE doesn't save them correctly
}
