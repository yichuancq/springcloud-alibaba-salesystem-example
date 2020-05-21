package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Country;
import com.example.request.PageRequest;

import java.util.List;

/**
 * @author yichuan
 */
public interface CountryService extends IService<Country> {

    /**
     * @return
     */
    List<Country> findAllCountry();

    /**
     * 条件分页查询
     *
     * @param pageRequest
     * @return
     */
    IPage<Country> findByPage(Country country,PageRequest pageRequest);

    /**
     * 添加或者更新国家信息
     *
     * @param country
     * @return
     */
    boolean saveOrUpdateCountry(Country country);

}
