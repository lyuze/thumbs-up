package com.lyz.thumbsup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ThumbsUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThumbsUpApplication.class, args);
    }

}
