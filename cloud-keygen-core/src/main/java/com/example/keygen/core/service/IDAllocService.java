package com.example.keygen.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.keygen.core.model.LeafAlloc;

import java.util.List;

/**
 * @calss name IDAllocService
 * @description: IDAllocService
 * @author: yichuan
 * @create time: 2020/06/06 16:31
 */
public interface IDAllocService extends IService<LeafAlloc> {

    /**
     * 查询全部记录
     *
     * @return
     */
    List<LeafAlloc> getAllLeafAlloc();

    /**
     * @param tag
     * @return
     */
    LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);

    /**
     * @param leafAlloc
     * @return
     */
    LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);

    /**
     * @return
     */
    List<String> getAllTags();
}
