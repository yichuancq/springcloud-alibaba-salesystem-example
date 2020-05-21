package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Country;

import java.util.List;

/**
 * @author yichuan
 */
public interface CountryService extends IService<Country> {

    /**
     * @return
     */
    List<Country> findAllCountry();
}
