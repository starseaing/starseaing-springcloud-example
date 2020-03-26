package com.starseaing.example.form;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.starseaing.example.form.mapper")
@EnableFeignClients
public class SpringcloudExampleFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudExampleFormApplication.class, args);
    }

}
