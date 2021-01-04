package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.SpecGroupMapper;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.service.SpecificationService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName SpecificationServiceImpl
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/4
 * @Version V1.0
 **/
@RestController
public class SpecificationServiceImpl extends BaseApiService implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecGroupEntity>> getSepcGroupInfo(SpecGroupDTO specGroupDTO) {
        Example example = new Example(SpecGroupEntity.class);

        if(ObjectUtil.isNotNull(specGroupDTO.getCid())){
            example.createCriteria().andEqualTo("cid",
                    BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class).getCid());
        }
        List<SpecGroupEntity> list = specGroupMapper.selectByExample(example);
        return this.setResultSuccess(list);
    }

    @Transactional
    @Override
    public Result<JSONObject> save(SpecGroupDTO specGroupDTO) {
        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> editSpecGroup(SpecGroupDTO specGroupDTO) {
        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specGroupDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> deleteSpecGroupInfo(Integer id) {

        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",id);
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        if(specParamEntities.size() != 0) return this.setResultError("绑定有规格组， 不能被删除");

        specGroupMapper.deleteByPrimaryKey(id);
        return  this.setResultSuccess();
    }

    //    ----------------------规格组------------------------------------
    @Override
    public Result<List<SpecParamEntity>> getSepcGroupInfo(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        Example example = new Example(SpecParamEntity.class);
        example.createCriteria().andEqualTo("groupId",specParamEntity.getGroupId());
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        return this.setResultSuccess(specParamEntities);
    }

    @Transactional  // 添加
    @Override
    public Result<JSONObject> save(SpecParamDTO specParamDTO) {

        specParamMapper.insertSelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Transactional  // 修改
    @Override
    public Result<JSONObject> editSpecParam(SpecParamDTO specParamDTO) {
          specParamMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
         return this.setResultSuccess();
    }

    @Transactional  // 删除
    @Override
    public Result<JSONObject> deleteSpecParamInfo(Integer id) {
        specParamMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }

}
