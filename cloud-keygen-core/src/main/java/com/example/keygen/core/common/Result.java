package com.example.keygen.core.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    private long id;
    private Status status;

    public Result() {

    }

    public Result(long id, Status status) {
        this.id = id;
        this.status = status;
    }

}
