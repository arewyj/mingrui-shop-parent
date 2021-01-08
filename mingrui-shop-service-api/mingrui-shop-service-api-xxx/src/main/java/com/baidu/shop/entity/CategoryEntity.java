package com.baidu.shop.entity;

import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName CategoryEntity
 * @Description: TODO
 * @Author wangyanjun
 * @Date 2020/12/22
 * @Version V1.0
 **/
@ApiModel(value = "分类实体类")
@Data
@Table(name="tb_category")
public class CategoryEntity {

    /*
    *
    * CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目id',
  `name` varchar(20) NOT NULL COMMENT '类目名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父类目id,顶级类目填0',
  `is_parent` tinyint(1) NOT NULL COMMENT '是否为父节点，0为否，1为是',
  `sort` int(4) NOT NULL COMMENT '排序指数，越小越靠前',
  PRIMARY KEY (`id`),
  KEY `key_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1424 DEFAULT CHARSET=utf8 COMMENT='商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系';*/
    @Id
    @ApiModelProperty(value ="类目id", example = "1")
    @NotNull(message = "ID不能为空",groups = {MingruiOperation.Update.class})
    private Integer id;


    @ApiModelProperty(value ="类目名称")
    @NotEmpty(message = "类目名称不能为空",groups = {MingruiOperation.Update.class,MingruiOperation.Add.class})
    private String name;

    @ApiModelProperty(value ="父类目id,顶级类目填0", example = "1")
    @NotNull(message = "父类目ID不能为Null",groups = {MingruiOperation.Add.class})
    private Integer parentId;

    @ApiModelProperty(value ="是否为父节点，0为否，1为是", example = "1")
    @NotNull(message = "是否为父节点不能为Null",groups = {MingruiOperation.Add.class})
    private Integer isParent;

    @ApiModelProperty(value ="排序指数，越小越靠前", example = "1")
    @NotNull(message = "父类目ID不能为空",groups = {MingruiOperation.Add.class})
    private Integer sort;
}
