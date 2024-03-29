package com.example.oauth.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author yichuan
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * @return
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("user").genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/").select()
                //过滤的接口
                .paths(or(regex("/.*"))).build().apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        ApiInfo apiInfo = new ApiInfo("oauth2-server-sample",
                //小标题
                "oauth2-server-sample",
                //版本
                "0.1", "NO terms of service",
                //作者
                "易川",
                //链接显示文字
                "yichuancq@163.com", "http://www.apache.org/licenses/LICENSE-2.0.html");
        return apiInfo;
    }
}
