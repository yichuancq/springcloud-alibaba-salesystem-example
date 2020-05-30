package com.example.common.exception;

/**
 * 自定义异常信息
 *
 * @author yichuan
 */
public class DataException extends Exception {
    /**
     * 错误编码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;

    public DataException() {
    }

    public DataException(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "DataException{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
