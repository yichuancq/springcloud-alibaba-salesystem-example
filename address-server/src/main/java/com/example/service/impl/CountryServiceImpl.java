package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CountryDao;
import com.example.domain.Country;
import com.example.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yichuan
 */
@Service
@Transactional
public class CountryServiceImpl extends ServiceImpl<CountryDao, Country>
        implements CountryService {

    @Autowired
    private CountryDao countryDao;

    /**
     * @return
     */
    @Override
    public List<Country> findAllCountry() {
        return countryDao.selectList(null);
    }
}
