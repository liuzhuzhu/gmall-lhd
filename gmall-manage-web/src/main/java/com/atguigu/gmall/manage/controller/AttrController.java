package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttrController {

    @Reference
    AttrService attrService;

    @RequestMapping("getAttrBaseInfoByC3id")
    @ResponseBody
    public List<BaseAttrInfo> getAttrBaseInfoByC3id(@RequestParam String c3id){

        List<BaseAttrInfo> attrInfoList = attrService.getAttrBaseInfoByC3id(c3id);
        return attrInfoList;
    }

    @RequestMapping("saveAttr")
    @ResponseBody
    public String saveAttr(BaseAttrInfo baseAttrInfo){

        attrService.saveAttr(baseAttrInfo);
        return "success";
    }
}
