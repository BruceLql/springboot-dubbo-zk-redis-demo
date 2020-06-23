package com.wuhao.controller;

import com.wuhao.domain.User;
import com.wuhao.redis.RedisTemplateService;
import com.wuhao.service.ProductService;
import com.wuhao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    RedisTemplateService redisTemplateService;

    @Autowired
    private ProductService productService;

    @Reference(version = "1.0.0")
    private UserService userServiceImpl;

    private Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户查询
     * @return
     */
    @RequestMapping("/getUser")
    public String getUserById(){
        Long id = 1L;
        String redisKey = "USER:ID:"+ id;
        User cacheUser = redisTemplateService.get(redisKey, User.class);
        if(cacheUser == null){
            log.info("redis 缓存没数据, 从数据库查询...");
            User dbUser = userServiceImpl.getUserById(id);
            System.out.println("=============:"+dbUser.toString());
            redisTemplateService.set(redisKey,dbUser);
            return dbUser.toString();
        }
        log.info("redis 缓存有数据, 从redis中查询...");
        return cacheUser.toString();
    }

    /**
     * 添加完 返回总共消费
     * @param a
     * @return
     */
    @RequestMapping("/add")
    public String getCost(Integer a){


        return "该产品总共消费 ："+productService.getCost(a);
    }
}
