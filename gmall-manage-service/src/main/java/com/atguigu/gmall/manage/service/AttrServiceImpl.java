package com.atguigu.gmall.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.BaseAttrInfo;
import com.atguigu.gmall.bean.BaseAttrValue;
import com.atguigu.gmall.manage.mapper.AttrBaseInfoMapper;
import com.atguigu.gmall.manage.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    AttrBaseInfoMapper attrBaseInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;
    @Override
    public List<BaseAttrInfo> getAttrBaseInfoByC3id(String c3id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(c3id);
        List<BaseAttrInfo> list = attrBaseInfoMapper.select(baseAttrInfo);
        return list;
    }

    /**
     * 新增 或者 修改
     * @param baseAttrInfo
     */
    @Override
    public void saveAttr(BaseAttrInfo baseAttrInfo) {

        String id = baseAttrInfo.getId();
        if(StringUtils.isBlank(id)){
            System.err.println("adddddddddddddddddddddddddddddddddddddddddddddd");
            //无主键的情况
            //@GeneratedValue(strategy = GenerationType.IDENTITY) 实体类中配置了，就会在插入的时候返回主键
            attrBaseInfoMapper.insert(baseAttrInfo);
            String attrId = baseAttrInfo.getId();
            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            for (BaseAttrValue baseAttrValue:attrValueList){
                baseAttrValue.setAttrId(attrId);
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }else{
            //更新操作
            attrBaseInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);


            Example example = new Example(BaseAttrValue.class);
            example.createCriteria().andEqualTo("attrId",baseAttrInfo.getId());
            baseAttrValueMapper.deleteByExample(example);

            String attrId = baseAttrInfo.getId();
            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            for (BaseAttrValue baseAttrValue:attrValueList){
                baseAttrValue.setAttrId(attrId);
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }

        }


    }
}
