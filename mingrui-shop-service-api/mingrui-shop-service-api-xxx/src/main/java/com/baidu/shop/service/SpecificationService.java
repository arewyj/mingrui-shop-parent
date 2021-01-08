package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SpecificationService
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/4
 * @Version V1.0
 **/
@Api(tags = "规格接口")
public interface SpecificationService {

       @ApiOperation(value = "通过条件查询规格组")
       @GetMapping(value = "specgroup/getSpecGroupInfo")
       Result<List<SpecGroupEntity>> getSepcGroupInfo(SpecGroupDTO specGroupDTO);

       //新增  specgroup/save
      @ApiOperation(value = "新增规格组")
      @PostMapping(value = "specgroup/save")
      Result<JSONObject> save(@Validated({MingruiOperation.Add.class}) @RequestBody SpecGroupDTO specGroupDTO);


    @ApiOperation(value = "修改规格组")
    @PutMapping(value = "specgroup/save")
    Result<JSONObject> editSpecGroup(@Validated({MingruiOperation.Update.class}) @RequestBody SpecGroupDTO specGroupDTO);

    //specgroup/deleteSpecGroupInfo  删除
    @ApiOperation(value = "删除规格组")
    @DeleteMapping(value = "specgroup/delete")
    Result<JSONObject> deleteSpecGroupInfo(Integer id);


    // ---------------规格组下的规格参数的 增删改查
    // specparam/list?
    @ApiOperation(value = "通过条件查询规格参数")
    @GetMapping(value = "specparam/list")
    Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO);

    // specparam/save
    @ApiOperation(value = "新增规格参数")
    @PostMapping(value = "specparam/save")
    Result<JSONObject> save(@Validated({MingruiOperation.Add.class}) @RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value = "修改规格参数")
    @PutMapping(value = "specparam/save")
    Result<JSONObject> editSpecParam(@Validated({MingruiOperation.Update.class}) @RequestBody SpecParamDTO specParamDTO);

    //specparam/del  删除
    @ApiOperation(value = "删除规格参数")
    @DeleteMapping(value = "specparam/del")
    Result<JSONObject> deleteSpecParamInfo(Integer id);

}
