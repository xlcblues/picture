package com.picture.picture_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.picture.picture_backend.mapper")
public class PictureBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PictureBackendApplication.class, args);
    }

}
