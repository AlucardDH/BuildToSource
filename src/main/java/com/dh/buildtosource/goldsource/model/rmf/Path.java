package com.dh.buildtosource.goldsource.model.rmf;

import lombok.Data;

@Data
public class Path {

//    string(128)    path name
    String pathName;
//    string(128)    path class (should be "path_corner" or "path_track")
    String pathClass;
//    int            path type: 0=one way, 1=circular, 2=ping-pong
    int type;
//    int            number of corners
    int cornersCount;
//    Corner[]       corners in the order they appear in the path
    Corner[] corners;

}
