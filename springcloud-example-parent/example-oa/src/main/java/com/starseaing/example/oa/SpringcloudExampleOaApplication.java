package com.starseaing.example.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringcloudExampleOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudExampleOaApplication.class, args);
    }

}
