package com.example.controller;

import com.example.exception.ResultCode;
import com.example.response.ResponseResultData;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 *
 * @author yichuan
 */
@Slf4j
@RestController
public class UserController {

    /**
     * sayHello
     *
     * @return
     */
    @ApiOperation(value = "/sayHello", notes = "for test")
    @GetMapping("/sayHello")
    public ResponseResultData<?> sayHello() {
        ResponseResultData resultData = new ResponseResultData();
        resultData.setData("Hello");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("intkey", 1);
        resultMap.put("strKey", "yichuan");
        resultData.setResultMap(resultMap);
        //自定义ResultCode
        ResultCode code = ResultCode.SUCCESS;
        resultData.setCode(code.code());
        resultData.setMessage(code.message());
        log.info("result:{}", resultData.toString());
        return resultData;
    }
}
