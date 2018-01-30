package com.timepack.reverseProxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: xumin
 * @CreateTime: 2018/01/17 14:07
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("【【【【【【 gateway 】】】】】】已启动.");
    }
}
