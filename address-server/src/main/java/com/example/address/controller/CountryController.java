package com.example.address.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.address.domain.Country;
import com.example.address.service.CountryService;
import com.example.common.request.PageRequest;
import com.example.common.response.ResponseResultData;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseResultData<?> findAllCountry(HttpServletRequest httpServletRequest) {
        try {
            log.info("token:{}", httpServletRequest.getHeader("token"));
            log.info("findAllCountry");
            List<Country> countryList = countryService.findAllCountry();
            return new ResponseResultData<>(200, countryList);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("error:{}", ex.getMessage());
        }
        return null;
    }

    /**
     * 分页查询所有国家信息列表
     *
     * @param pageRequest
     * @return
     */
    @ApiOperation(value = "/findPagerCountry", notes = "分页查询所有国家信息列表")
    @PostMapping("/findPagerCountry")
    public ResponseResultData<?> findPagerCountry(@RequestBody Country queryCountry,
                                                  @ModelAttribute PageRequest pageRequest, HttpServletRequest httpServletRequest) {
        try {
            log.info("token:{}", httpServletRequest.getHeader("token"));
            log.info("pageRequest:{}", pageRequest.toString());
            log.info("queryCountry:{}", queryCountry.toString());
            //需要在Config配置类中配置分页插件
            IPage<Country> page = countryService.findByPage(queryCountry, pageRequest);
            return new ResponseResultData<>(200, page);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("error:{}", ex.getMessage());
        }
        return null;
    }

    /**
     * 添加或者更新国家信息
     *
     * @param country
     * @return
     */
    @ApiOperation(value = "/saveCountry", notes = "添加或者更新国家信息")
    @PostMapping("/saveCountry")
    public ResponseResultData<?> saveCountry(@RequestBody Country country, HttpServletRequest httpServletRequest) {
        try {
            log.info("token:{}", httpServletRequest.getHeader("token"));
            log.info("country:{}", country.toString());
            boolean flag = countryService.saveOrUpdateCountry(country);
            return new ResponseResultData<>(200, flag);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("error:{}", ex.getMessage());
        }
        return null;
    }


}