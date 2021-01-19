package com.baidu.shop.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SpuDetailEntity
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/18
 * @Version V1.0
 **/
@Table(name = "tb_spu_detail")
@Data
public class SpuDetailEntity {

    @Id
    private Integer spuId;
    // 商品描述信息
    private String description;
    // 通用规格参数数据
    private String genericSpec;
    // 特有规格参数及可选值信息
    private String specialSpec;
    // 包装清单
    private String packingList;
    // 售后服务
    private String afterService;



}
