package com.dh.buildtosource.goldsource.model.rmf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbstactObject {

    String type;
    int visGroup;
    Color color;

    AbstactObject(String type) {
        this.type = type;
    }


}
