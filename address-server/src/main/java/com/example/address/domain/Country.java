package com.example.address.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yichuan
 */
@Getter
@Setter
@ToString
@TableName("country")//@TableName中的值对应着表名
public class Country implements Serializable {

    @TableId(type = IdType.AUTO)
    private Short countryId;

    private String country;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

}