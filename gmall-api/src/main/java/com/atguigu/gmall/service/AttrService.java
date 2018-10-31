package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.BaseAttrInfo;

import java.util.List;

public interface AttrService {
     List<BaseAttrInfo> getAttrBaseInfoByC3id(String c3id);

     void saveAttr(BaseAttrInfo baseAttrInfo);
}
