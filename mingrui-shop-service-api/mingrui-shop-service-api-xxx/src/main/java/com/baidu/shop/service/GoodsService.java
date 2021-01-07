package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SkuDTO;
import com.baidu.shop.dto.SpuDTO;
import com.baidu.shop.entity.SpuDetailEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/5
 * @Version V1.0
 **/
@Api(tags = "商品接口")
public interface GoodsService {

    @ApiOperation(value = "获取spu信息")
    @GetMapping(value = "goods/getSpuInfo")
    Result<List<SpuDTO>> getSupInfo(SpuDTO spuDTO);

    @ApiOperation(value = "添加商品信息")
    @PostMapping(value = "goods/save")
    Result<JSONObject> saveGoods(@RequestBody SpuDTO spuDTO);


    // /goods/getSpuDetailBySpuId
    @ApiOperation(value = "通过spuid查询SpuDetail的信息")
    @GetMapping(value = "goods/getSpuDetailBySpuId")
    Result<SpuDetailEntity> getSpuDetailBySpuId(Integer spuId);

    // goods/getSkuBySpuId
    @ApiOperation(value = "通过spuid查询sku的信息")
    @GetMapping(value = "goods/getSkuBySpuId")
    Result<List<SkuDTO>> getSkuBySpuId(Integer spuId);
}
