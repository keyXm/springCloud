package com.timepack.serve.controller;

import java.util.List;

import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timepack.serve.entity.User;
import com.timepack.serve.service.IUserService;

/**
 * 用户微服务Controller。
 * @Author: xumin
 * @CreateTime: 2018/01/17 14:07
 *
 */
@RestController
public class ProviderUserMysqlMybatisController implements HystrixServeHi {
	private static final Logger Logger = LoggerFactory.getLogger(ProviderUserMysqlMybatisController.class);
   
	@Autowired
    private IUserService iUserService;

    @GetMapping("/simple/{id}")
    public User findUserById(@PathVariable Long id) {
    	System.out.println("findUserById--"+id);
        return this.iUserService.findUserById(id);
    }

    @GetMapping("/simple/list")
    public List<User> findUserList() {
        return this.iUserService.findAllByBean(new User());
    }

    /**
     * 添加一个student,使用postMapping接收post请求
     *
     * http://localhost:8310/simple/addUser?username=user11&age=11&balance=11
     *
     * @return
     */
    @PostMapping("/simple/addUser")
    public User addUser(@RequestParam(value = "username", required=false) String username,
    		@RequestParam(value = "age", required=false) Integer age, 
    		@RequestParam(value = "balance", required=false) String balance){
        User user=new User();

        user.setUsername(username);
        user.setName(username);
        user.setAge(age);
        user.setBalance(balance);

        int result = iUserService.insertUser(user);
        if(result > 0){
            return user;
        }

        user.setId(0L);
        user.setName(null);
        user.setUsername(null);
        user.setBalance(null);
        return user;
    }
    @GetMapping("/user/ehcache")
    public String ehcache() {
        Logger.info("===========  进行Encache缓存测试");
        List<User> allUsers = iUserService.findAllByBean(new User());
        User lastUser = allUsers.get(allUsers.size() - 1);
        String lastUserUsername = lastUser.getUsername();
        String indexString = lastUserUsername.substring(4);

        Logger.info("===========  ====生成第一个用户====");
        User user1 = new User();
        //生成第一个用户的唯一标识符 UUID
        user1.setName("user" + (Integer.parseInt(indexString) + 1));
        user1.setUsername(user1.getName());
        user1.setAge(1000);
        user1.setBalance("1000");
        if (iUserService.insertUser(user1) == 0){
            throw new CacheException("用户对象插入数据库失败");
        }

        allUsers = iUserService.findAllByBean(new User());
        lastUser = allUsers.get(allUsers.size() - 1);
        Long lastUserId = lastUser.getId();

        //第一次查询
        Logger.info("===========  第一次查询");
        Logger.info("===========  第一次查询结果: {}", iUserService.findUserById(lastUserId));
        //通过缓存查询
        Logger.info("===========  通过缓存第 1 次查询");
        Logger.info("===========  通过缓存第 1 次查询结果: {}", iUserService.findUserById(lastUserId));
        Logger.info("===========  通过缓存第 2 次查询");
        Logger.info("===========  通过缓存第 2 次查询结果: {}", iUserService.findUserById(lastUserId));
        Logger.info("===========  通过缓存第 3 次查询");
        Logger.info("===========  通过缓存第 3 次查询结果: {}", iUserService.findUserById(lastUserId));

        Logger.info("===========  ====准备修改数据====");
        User user2 = new User();
        user2.setName(lastUser.getName());
        user2.setUsername(lastUser.getUsername());
        user2.setAge(lastUser.getAge() + 1000);
        user2.setBalance(String.valueOf(user2.getAge()));
        user2.setId(lastUserId);
        try {
            iUserService.updateUser(user2);
            Logger.info("===========  ==== 修改数据 == {} ==", "执行成功");
        } catch (CacheException e){
            e.printStackTrace();
        }
        Logger.info("===========  ====修改后再次查询数据 ");
        User resultObj = iUserService.findUserById(lastUser.getId());
        Logger.info("===========  ====修改后再次查询数据结果: {}", resultObj);
        return "success";
    }

	@Override
	public String sayHiFromClientOne(String name) {
		return "sorry"+name;
	}
}