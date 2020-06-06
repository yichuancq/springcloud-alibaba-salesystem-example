package com.example.keygen.core.service;

import com.example.keygen.core.common.Result;


/**
 * ID生成服务
 */
public interface IDGen {
    /**
     * 通过标识获取ID
     *
     * @param key
     * @return
     */
    Result get(String key);

    /**
     * 初始化
     *
     * @return
     */
    boolean init();
}
