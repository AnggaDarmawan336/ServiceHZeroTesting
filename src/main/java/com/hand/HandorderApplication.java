package com.hand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.choerodon.resource.annoation.EnableChoerodonResourceServer;

@EnableChoerodonResourceServer
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.hand.infra.mapper")
public class HandorderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandorderApplication.class, args);
    }
}


