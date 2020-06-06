package com.example.keygen.core.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckVO {
    private long timestamp;
    private int workID;

    public CheckVO(long timestamp, int workID) {
        this.timestamp = timestamp;
        this.workID = workID;
    }

}
