package com.web.whalecluod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.web.whalecluod.mapper")
public class WhalecluodApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhalecluodApplication.class, args);
    }

}
