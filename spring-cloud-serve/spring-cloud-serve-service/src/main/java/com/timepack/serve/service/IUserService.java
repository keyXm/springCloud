package com.timepack.serve.service;

import java.util.List;

import com.timepack.serve.entity.User;

/**
 * 简单用户链接Mysql数据库微服务（通过@Service注解标注该类为持久化操作对象）。
 * @Author: xumin
 * @CreateTime: 2018/01/17 14:07
 */
public interface IUserService {

	User findUserById(Long id);
	
	List<User> findAllByBean(User bean);

	int insertUser(User bean);
	
	User updateUser(User bean);
	
	int deleteUser(long id);
}