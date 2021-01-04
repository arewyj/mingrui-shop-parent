package com.baidu.shop.entity;

import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @ClassName BrandEntity
 * @Description: TODO
 * @Author wangyanjun
 * @Date 2020/12/25
 * @Version V1.0
 **/
@Table(name="tb_brand")
@Data
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @ApiModelProperty(value ="品牌id", example = "1")
  //  @NotNull(message = "品牌ID不能为空",groups = {MingruiOperation.Update.class})
    private Integer id;

   // @ApiModelProperty(value = "品牌名称")
   // @NotEmpty(message = "品牌名称不能为空",groups = {MingruiOperation.Update.class,MingruiOperation.Add.class})
    private String name;

  //  @ApiModelProperty(value = "品牌图片")
    //@NotNull(message = "品牌图片logo不能为空",groups = {MingruiOperation.Add.class})
    private String image;

  //  @ApiModelProperty(value = "品牌首字母")
  //  @NotNull(message = "品牌首字母不能为空",groups = {MingruiOperation.Add.class})
    private Character letter;
}
