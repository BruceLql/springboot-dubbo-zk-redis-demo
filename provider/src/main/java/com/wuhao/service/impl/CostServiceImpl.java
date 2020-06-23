package com.wuhao.service.impl;
/*
 *@ClassName CostServiceImpl
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/23 15:09
 *@Version 1.0
 */

import com.wuhao.service.CostService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0", interfaceClass = CostService.class)
public class CostServiceImpl implements CostService {
    /**
     * 假设之前总花费了100
     */
    private final Integer totalCost = 1000;

    /**
     * 之前总和 加上 最近一笔
     * @param cost
     * @return
     */
    @Override
    public Integer add(int cost) {
        System.out.println("服务提供者 ===========add ");
        return totalCost + cost;
    }
}