package com.timepack.serve.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xumin
 * @CreateTime: 2018/01/17 14:07
 */

@FeignClient(value = "serve-hi",fallback = HystrixServeHi.class)
public interface  HystrixServeHi {
	 @RequestMapping(value = "/hi",method = RequestMethod.GET)
	 String sayHiFromClientOne(@RequestParam(value = "name") String name);
}