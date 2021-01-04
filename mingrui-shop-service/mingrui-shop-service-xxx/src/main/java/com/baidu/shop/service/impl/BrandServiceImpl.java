package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.PinyinUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BrandServiceImpl
 * @Description: TODO
 * @Author wangyanjun
 * @Date 2020/12/25
 * @Version V1.0
 **/
@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public Result<PageInfo<BrandEntity>> getBrandData(BrandDTO brandDTO) {
        // 分页
        PageHelper.startPage(brandDTO.getPage(), brandDTO.getRows());

        // 排序
        if (!StringUtils.isEmpty(brandDTO.getSort())) PageHelper.orderBy(brandDTO.getOrderBy());

        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);

        Example example = new Example(BrandEntity.class);

        example.createCriteria().andLike("name", "%" + brandEntity.getName() + "%");

        List<BrandEntity> brandEntities = brandMapper.selectByExample(example);
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntities);

        return this.setResultSuccess(pageInfo);
    }



    @Transactional
    @Override
    public Result<JSONObject> saveBrandInfo(BrandDTO brandDTO) {
        // 新增返回
        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);
        //处理品牌首字母
        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]),false).toCharArray()[0]);

        brandMapper.insertSelective(brandEntity);

        this.insertCategoryBrandList(brandDTO.getCategories(),brandDTO.getId());

        return this.setResultSuccess();
    }


    @Transactional  // 删除
    @Override
    public Result<JSONObject> deleteBrandInfo(Integer id) {
        brandMapper.deleteByPrimaryKey(id);

       /* Example example = new Example(CategoryBrandEntity.class);

        example.createCriteria().andEqualTo("brandId",id);
        categoryBrandMapper.deleteByExample(example);*/

        this.deleteCategoryBrandByBrandId(id);

        return this.setResultSuccess();
    }


    ///  修改
    @Override
    public Result<JSONObject> editBrandInfo(BrandDTO brandDTO) {


        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);


        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]), false).toCharArray()[0]);
        brandMapper.updateByPrimaryKeySelective(brandEntity);


      /*  Example example = new Example(CategoryBrandEntity.class);

        example.createCriteria().andEqualTo("brandId",brandEntity.getId());
        categoryBrandMapper.deleteByExample(example);*/

        this.deleteCategoryBrandByBrandId(brandDTO.getId());

       /* //维护中间表数据
        String categories = brandDTO.getCategories();//得到分类集合字符串
        if (StringUtils.isEmpty(brandDTO.getCategories())) return this.setResultError("");

        //判断分类集合字符串中是否包含,
        if (categories.contains(",")) {//多个分类 --> 批量新增
            categoryBrandMapper.insertList(
                    Arrays.asList(categories.split(","))
                            .stream()
                            .map(categoryIdStr -> new CategoryBrandEntity(Integer.valueOf(categoryIdStr),brandEntity.getId()))
                            .collect(Collectors.toList()));
        } else { // 普通单个新增
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setBrandId(brandEntity.getId());
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));

            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }*/

        this.insertCategoryBrandList(brandDTO.getCategories(),brandDTO.getId());

        return this.setResultSuccess();
    }


    // 删除的发公共 方法
    private void insertCategoryBrandList(String categories,Integer brandId){
        //将公共的代码抽取出来
        //看是否需要返回值
        //看抽取出来的方法是否需要别的类调用
        //抽取出来的代码哪里报错,查看报错信息,用方法的参数代替(可变的内容当做方法的参数)
        //如果有不重要的返回值代码 --> 手动抛自定义异常(全局异常处理会帮我们处理)
        //维护中间表数据
        // String categories = brandDTO.getCategories();//得到分类集合字符串
        if (StringUtils.isEmpty(categories)) throw  new RuntimeException("分类信息不能为空");

        //判断分类集合字符串中是否包含,
        if (categories.contains(",")) {//多个分类 --> 批量新增
            categoryBrandMapper.insertList(
                    Arrays.asList(categories.split(","))
                            .stream()
                            .map(categoryIdStr -> new CategoryBrandEntity(Integer.valueOf(categoryIdStr),brandId))
                            .collect(Collectors.toList()));
        } else { // 普通单个新增
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setBrandId(brandId);
            categoryBrandEntity.setCategoryId(Integer.valueOf(categories));
            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }

    }


    // 修改和 删除 里公共的 删除 关系表的代码  提取出来
    private void deleteCategoryBrandByBrandId(Integer brandId){
        Example example = new Example(CategoryBrandEntity.class);

        example.createCriteria().andEqualTo("brandId",brandId);
        categoryBrandMapper.deleteByExample(example);
    }

}


