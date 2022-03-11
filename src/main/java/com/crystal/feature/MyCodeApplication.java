package com.crystal.feature;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author CHUNHAO LIU
 */
@SpringBootApplication
@NacosPropertySource(dataId = "common-crystal.yaml", autoRefreshed = true)
@MapperScan("com.crystal.feature.mapper")
@ComponentScan(basePackages = {"com.crystal.feature.*"})
public class MyCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCodeApplication.class, args);
    }

}
