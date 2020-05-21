package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.domain.Country;
import com.example.request.PageRequest;
import com.example.response.ResponseResultData;
import com.example.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * country
 *
 * @author yichuan
 */
@Slf4j
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * 查询所有国家信息列表
     *
     * @return
     */
    @ApiOperation(value = "/findAllCountry", notes = "查询所有国家信息列表")
    @GetMapping("/findAllCountry")
    @ResponseBody
    public ResponseResultData<?> findAllCountry() {
        try {
            log.info("findAllCountry");
            List<Country> countryList = countryService.findAllCountry();
            return new ResponseResultData<>(200, countryList);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("error:{}", ex.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "/findPagerCountry", notes = "分页查询所有国家信息列表")
    @PostMapping("/findPagerCountry")
    @ResponseBody
    public ResponseResultData<?> findPagerCountry(@RequestBody PageRequest pageRequest) {
        try {
            log.info("pageRequest:{}", pageRequest.toString());
            //需要在Config配置类中配置分页插件
            IPage<Country> page = countryService.findByPage(pageRequest);
            return new ResponseResultData<>(200, page);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("error:{}", ex.getMessage());
        }
        return null;
    }


}
