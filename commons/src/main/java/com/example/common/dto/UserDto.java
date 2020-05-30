package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * UserDto
 *
 * @author yichuan
 */
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
    private String userName;
    private String password;
}
