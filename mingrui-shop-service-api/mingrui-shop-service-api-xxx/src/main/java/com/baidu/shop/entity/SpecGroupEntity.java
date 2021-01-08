package com.baidu.shop.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SpecGroupEntity
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/4
 * @Version V1.0
 **/
@Table(name="tb_spec_group")
@Data
public class SpecGroupEntity {
    /*
    * CREATE TABLE `tb_spec_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cid` bigint(20) NOT NULL COMMENT '商品分类id，一个分类下有多个规格组',
  `name` varchar(50) NOT NULL COMMENT '规格组的名称',
  PRIMARY KEY (`id`),
  KEY `key_category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='规格参数的分组表，每个商品分类下有多个规格参数组';*/
    @Id
    private Integer id;

    private Integer cid;

    private String name;
}
