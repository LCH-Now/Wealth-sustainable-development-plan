package com.crystal.feature;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author CHUNHAO LIU
 */
@SpringBootApplication
@MapperScan("com.crystal.feature.mapper")
@ComponentScan(basePackages = {"com.crystal.feature.*","com.skq.core.jwt.service"})
public class MyCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCodeApplication.class, args);
    }

}
