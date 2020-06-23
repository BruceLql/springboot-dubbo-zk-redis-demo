package com.wuhao.service.impl;
/*
 *@ClassName ProductServiceImpl
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/23 15:13
 *@Version 1.0
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuhao.service.CostService;
import com.wuhao.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 使用dubbo的注解 com.alibaba.dubbo.config.annotation.Reference。进行远程调用service
     */
    @Reference(version = "1.0.0")
    private CostService costService;

    @Override
    public Integer getCost(Integer a) {
        System.out.println("impl ========== costService 对象："+costService);
        return costService.add(a);
    }

}