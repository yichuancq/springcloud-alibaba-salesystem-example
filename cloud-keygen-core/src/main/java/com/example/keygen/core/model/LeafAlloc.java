package com.example.keygen.core.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * LeafAlloc
 *
 * @author yichuan
 */
@Getter
@Setter
@ToString
@Data
public class LeafAlloc {
    private String bizTag;
    private long maxId;
    private int step;
    private String updateTime;
}
