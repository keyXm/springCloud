package com.timepack.serve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 链接Mysql数据库简单的集成Mybatis框架访问数据库。
 * @author xumin
 * @version 0.0.1
 * @date 2017-10-19
 */
@SpringBootApplication
@MapperScan("com.timepack.serve.mapper.**")
@EnableEurekaClient
@EnableCaching
@EnableHystrix
@EnableHystrixDashboard
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("【【【【【【 链接MysqlMybatis数据库微服务 】】】】】】已启动.");
	}
	
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
