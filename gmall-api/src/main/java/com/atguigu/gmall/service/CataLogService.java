package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.BaseCatalog1;
import com.atguigu.gmall.bean.BaseCatalog2;
import com.atguigu.gmall.bean.BaseCatalog3;

import java.util.List;

public interface CataLogService {

     List<BaseCatalog1> getCatalog1();

     List<BaseCatalog2> getCatalog2ByCataLog1Id(String c1id);

     List<BaseCatalog3> getCatalog3ByCataLog2Id(String c2id);
}
