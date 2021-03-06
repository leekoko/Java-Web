package com.leekoko;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leekoko.mapper")
public class BadGeniusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BadGeniusApplication.class, args);
    }

}
