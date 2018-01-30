package com.timepack.serve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.timepack.serve.entity.User;
import com.timepack.serve.mapper.UserMapper;
import com.timepack.serve.service.IUserService;

/**
 * 简单用户链接Mysql数据库微服务（通过@Service注解标注该类为持久化操作对象）。<br/>
 *
 * <li>注意：CACHE_KEY、CACHE_NAME_B 的单引号不能少，否则会报错，被识别是一个对象。</li>
 *
 * <li>value 指的是 ehcache.xml 中的缓存策略空间；key 指的是缓存的标识</li>
 *
 * @author xumin
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	private static final String CACHE_KEY = "'user'";
	private static final String CACHE_NAME_B = "cache-b";
   
    @Autowired
    private UserMapper userMapper;

    @Cacheable(value=CACHE_NAME_B, key="'user_'+#id")
    @Override
    public User findUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

	@Override
	public List<User> findAllByBean(User bean) {
		return userMapper.getList(bean);
	}

	/**
     * 保存用户数据
     *
     * @param user
     * @return
     */
    @CacheEvict(value=CACHE_NAME_B, key=CACHE_KEY)
    @Override
	public int insertUser(User bean) {
		return userMapper.insertSelective(bean);
	}
    
    /**
     * 更新用户数据
     *
     * @param user
     * @return
     */
    @CachePut(value = CACHE_NAME_B, key = "'user_'+#user.id")
    @Override
    public User updateUser(User user) {
         userMapper.updateByPrimaryKeySelective(user);
      return user;
    }

    /**
     * 删除用户数据
     *
     * @param id
     * @return
     */
    @CacheEvict(value = CACHE_NAME_B, key = "'user_' + #id") //这是清除缓存
    @Override
	public int deleteUser(long id) {
		return userMapper.deleteByPrimaryKey(id);
	}
    
    
}