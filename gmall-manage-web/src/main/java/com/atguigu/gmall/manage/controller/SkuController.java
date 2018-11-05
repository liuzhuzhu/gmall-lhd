package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.SkuInfo;
import com.atguigu.gmall.service.SkuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SkuController {

    @Reference
    SkuInfoService skuInfoService;

    @RequestMapping("skuInfoListBySpu")
    @ResponseBody
    public List<SkuInfo> skuInfoListBySpu(@RequestParam("spuId") String spuId){
        return skuInfoService.skuInfoListBySpu(spuId);
    }
    @RequestMapping("saveSku")
    @ResponseBody
    public List<SkuInfo> saveSku(@RequestParam("skuInfo") SkuInfo skuInfo){
        return null;
    }
}
