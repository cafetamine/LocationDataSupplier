package com.actilive.lds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LdsApplication {

    public static void main(final String... args) {
        SpringApplication.run(LdsApplication.class, args);
    }

}
