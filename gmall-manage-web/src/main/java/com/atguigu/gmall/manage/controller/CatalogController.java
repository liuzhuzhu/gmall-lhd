package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.BaseCatalog1;
import com.atguigu.gmall.bean.BaseCatalog2;
import com.atguigu.gmall.bean.BaseCatalog3;
import com.atguigu.gmall.service.CataLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CatalogController {

    @Reference
    CataLogService cataLogService;

    /**
     * 获取商品属性一级类目
     * @return
     */
    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatalog1(){

        List<BaseCatalog1> list = cataLogService.getCatalog1();

        return list;
    }

    /**
     * 获取商品属性二级类目
     * @return
     */
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(@RequestParam String c1id){
        List<BaseCatalog2> list = cataLogService.getCatalog2ByCataLog1Id(c1id);
        System.out.println("对应二级类目"+list);
        return list;
    }

    /**
     * 获取商品属性三级类目
     * @return
     */
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(@RequestParam String c2id){
        List<BaseCatalog3> list = cataLogService.getCatalog3ByCataLog2Id(c2id);
        System.out.println("对应三级类目"+list);
        return list;
    }
}
