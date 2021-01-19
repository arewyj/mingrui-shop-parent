package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BrandService
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/19
 * @Version V1.0
 **/
@Api(tags = "品牌接口")
public interface BrandService {

    @ApiOperation(value = "查看品牌列表")
    @GetMapping(value = "brand/list")
    Result<PageInfo<BrandEntity>> getBrandData(BrandDTO brandDTO);


    @ApiOperation(value = "添加品牌信息")
    @PostMapping(value = "brand/save")
    Result<JSONObject> saveBrandInfo(@Validated({MingruiOperation.Add.class}) @RequestBody BrandDTO brandDTO);


    @ApiOperation(value = "修改品牌信息")
    @PutMapping(value = "brand/save")
    Result<JSONObject> editBrandInfo(@Validated({MingruiOperation.Update.class}) @RequestBody BrandDTO brandDTO);


    @ApiOperation(value = "删除品牌信息")
    @DeleteMapping(value = "brand/delete")
    Result<JSONObject> deleteBrandInfo(Integer id);

    @ApiOperation(value = "通过分类id查询品牌")
    @GetMapping(value = "brand/getBrandInfoByCategoryId")
    Result<List<BrandEntity>> getBrandInfoByCategoryId(Integer cid);

}
