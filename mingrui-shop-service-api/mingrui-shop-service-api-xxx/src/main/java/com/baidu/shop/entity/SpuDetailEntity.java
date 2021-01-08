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
 * @Date 2021/1/7
 * @Version V1.0
 **/
@Table(name = "tb_spu_detail")
@Data
public class SpuDetailEntity {
    /*
    * CREATE TABLE `tb_spu_detail` (
  `spu_id` bigint(20) NOT NULL,
  `description` text COMMENT '商品描述信息',
  `generic_spec` varchar(3000) NOT NULL DEFAULT '' COMMENT '通用规格参数数据',
  `special_spec` varchar(1000) NOT NULL COMMENT '特有规格参数及可选值信息，json格式',
  `packing_list` varchar(1000) DEFAULT '' COMMENT '包装清单',
  `after_service` varchar(1000) DEFAULT '' COMMENT '售后服务',
  PRIMARY KEY (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/

    @Id
    private Integer spuId;

    private String description; // 商品描述信息

    private String genericSpec;  // 通用规格参数数据

    private String specialSpec;  // 特有规格参数及可选值信息

    private String packingList;  // 包装清单

    private String afterService; // 售后服务



}
