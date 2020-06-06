package com.example.keygen.core.controller;

import com.example.keygen.core.common.Result;
import com.example.keygen.core.service.IDGen;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @calss name LeafController
 * @description: LeafController
 * @author: yichuan
 * @create time: 2020/06/06 18:07
 */
@RestController
public class LeafController {

    @Autowired
    private IDGen idGen;

    /**
     * http://localhost:8082/api/segment/get/pay
     *
     * @param key
     * @return
     */
    @ApiOperation(value = "/api/segment/get", notes = "获取ID")
    @GetMapping(value = "/api/segment/get/{key}")
    public String getSegmentId(@PathVariable("key") String key) {

        keyInit();
        Result result = idGen.get("pay");
        System.out.println(result);
        return String.valueOf(result.getId());
    }

    private void keyInit() {
        if (idGen != null) {
            idGen.init();
        }
    }
}
