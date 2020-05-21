package com.example.response;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回响应结果
 *
 * @author yichuan
 */
public class ResponseResultData<T> implements Serializable {
    /**
     * 操作码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 结果
     */
    private T data;
    /**
     * map数据
     */
    private Map<String, Object> resultMap;

    public ResponseResultData() {
    }

    public ResponseResultData(Integer code, T data) {
        this.data = data;
        this.code = code;
    }

    public ResponseResultData(Integer code, String message, T data) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public ResponseResultData(Integer code, String message, T data, Map resultMap) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.resultMap = resultMap;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseResultData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", resultMap=" + resultMap +
                '}';
    }
}
