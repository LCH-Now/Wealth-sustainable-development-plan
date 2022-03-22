package com.crystal.feature.common.config;

import com.skq.core.jwt.aop.RestAuthTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.*;
/**
 * @author CHUNHAO LIU
 * 登录权限管理Config类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{


    @Bean
    public RestAuthTokenInterceptor restAuthTokenInterceptor(){
        RestAuthTokenInterceptor restAuthTokenInterceptor=new RestAuthTokenInterceptor();
        //精确过滤不进行登录token校验的访问，路径要加controller的路径Mapping
        List<String> excludeUrls=new ArrayList<String>();
        //excludeUrls.add("happyEight/queryNumberNoAppearsListByStage");

        //关键词过滤不进行登录token校验的访问
        List<String> excludeContainUrls=new ArrayList<String>();
        excludeContainUrls.add("/register/userRegister");
        //配置不进行接口访问频率限制的接口,关键词过滤
        List<String> excludeNotForbiddenUrls=new ArrayList<String>();
        excludeNotForbiddenUrls.add("/test");

        restAuthTokenInterceptor.setExcludeUrls(excludeUrls);
        restAuthTokenInterceptor.setExcludeContainUrls(excludeContainUrls);
        restAuthTokenInterceptor.setExcludeNotForbiddenUrls(excludeNotForbiddenUrls);
        return restAuthTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(restAuthTokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/**");
    }

}
