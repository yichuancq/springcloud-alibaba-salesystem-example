package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CountryDao;
import com.example.domain.Country;
import com.example.request.PageRequest;
import com.example.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yichuan
 */
@Slf4j
@Service
@Transactional
public class CountryServiceImpl extends ServiceImpl<CountryDao, Country> implements CountryService {

    @Autowired
    private CountryDao countryDao;

    /**
     * @return
     */
    @Override
    public List<Country> findAllCountry() {
        return countryDao.selectList(null);
    }

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    @Override
    public IPage<Country> findByPage(PageRequest pageRequest) {

        log.info("pageRequest:{}", pageRequest.toString());
        //需要在Config配置类中配置分页插件
        IPage<Country> pageParams = new Page<>();
        //当前页
        pageParams.setCurrent(pageRequest.getPageNumber());
        //每页条数
        pageParams.setSize(pageRequest.getPageSize());
        pageParams = this.page(pageParams);
        return pageParams;
    }

    /**
     * 添加或者更新国家信息
     *
     * @param country
     * @return
     */
    @Override
    public boolean saveOrUpdateCountry(Country country) {
        if (country == null) {
            return false;
        }
        //最后修改时间
        country.setLastUpdate(new Date());
        return this.saveOrUpdate(country);
    }
}
