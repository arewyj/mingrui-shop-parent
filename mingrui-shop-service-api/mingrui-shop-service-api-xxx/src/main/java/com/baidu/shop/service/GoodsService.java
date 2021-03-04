package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SkuDTO;
import com.baidu.shop.dto.SpuDTO;
import com.baidu.shop.entity.SpuDetailEntity;
import com.baidu.shop.validate.group.MingruiOperation;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/19
 * @Version V1.0
 **/
@Api(tags = "商品接口")
public interface GoodsService {

    @ApiOperation(value = "获取spu信息")
    @GetMapping(value = "goods/getSpuInfo")
    Result<List<SpuDTO>> getSpuInfo(@SpringQueryMap SpuDTO spuDTO);

    @ApiOperation(value = "添加商品信息")
    @PostMapping(value = "goods/save")
    Result<JSONObject> saveGoods(@Validated({MingruiOperation.Add.class}) @RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "修改商品信息")
    @PutMapping(value = "goods/save")
    Result<JSONObject> editGoods(@Validated({MingruiOperation.Update.class}) @RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "删除商品信息")
    @DeleteMapping(value = "goods/delete")
    Result<JSONObject> deleteGoods(Integer spuId);


    @ApiOperation(value = "通过spuid查询SpuDetail的信息")
    @GetMapping(value = "goods/getSpuDetailBySpuId")
    Result<SpuDetailEntity> getSpuDetailBySpuId(@RequestParam  Integer spuId);


    @ApiOperation(value = "通过spuid查询sku的信息")
    @GetMapping(value = "goods/getSkuBySpuId")
    Result<List<SkuDTO>> getSkuBySpuId(@RequestParam Integer spuId);


    @ApiOperation(value = "商品上架和下架")
    @PutMapping(value = "goods/alterSaleable")
    Result<JSONObject> alterSaleable(@Validated({MingruiOperation.Update.class}) @RequestBody SpuDTO spuDTO);

}
