package com.starseaing.example.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.starseaing.example.usercenter.mapper")
@EnableFeignClients
public class SpringcloudExampleUsercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudExampleUsercenterApplication.class, args);
    }

}
