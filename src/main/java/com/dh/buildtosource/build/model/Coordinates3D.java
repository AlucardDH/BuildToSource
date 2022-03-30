package com.dh.buildtosource.build.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Coordinates3D extends Coordinates2D {

  private int z;

}
