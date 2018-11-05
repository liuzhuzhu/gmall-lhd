package com.atguigu.gmall.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manage.mapper.*;
import com.atguigu.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    SpuInfoMapper spuInfoMapper;
    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    SpuImageMapper spuImageMapper;
    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpu(SpuInfo spuInfo) {
        // 保存spu信息，生成spu的主键
        spuInfoMapper.insertSelective(spuInfo);
        String spuInfoId = spuInfo.getId();

        // 保存销售属性信息
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuInfoId);
            spuSaleAttrMapper.insertSelective(spuSaleAttr);

            // 保存销售属性值
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                spuSaleAttrValue.setSpuId(spuInfoId);

                spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            }

        }

        // 保存图片信息
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : spuImageList) {
            spuImage.setSpuId(spuInfoId);
            spuImageMapper.insertSelective(spuImage);
        }
    }

    @Override
    public List<SpuInfo> spuList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        return spuInfoMapper.select(spuInfo);
    }
    @Override
    public List<SpuSaleAttr> spuSaleAttrList(String spuId) {

        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuId);
        List<SpuSaleAttr> select = spuSaleAttrMapper.select(spuSaleAttr);

        if(null!=select&&select.size()>0){
            for (SpuSaleAttr saleAttr : select) {

                SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
                spuSaleAttrValue.setSpuId(spuId);
                spuSaleAttrValue.setSaleAttrId(saleAttr.getSaleAttrId());
                List<SpuSaleAttrValue> select1 = spuSaleAttrValueMapper.select(spuSaleAttrValue);

                saleAttr.setSpuSaleAttrValueList(select1);
            }
        }
        return select;
    }
}
