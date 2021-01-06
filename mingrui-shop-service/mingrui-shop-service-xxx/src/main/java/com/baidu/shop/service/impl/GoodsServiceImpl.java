package com.baidu.shop.service.impl;

import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpuDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.entity.SpuEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.mapper.SpuMapper;
import com.baidu.shop.service.GoodsService;
import com.baidu.shop.status.HTTPStatus;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName GoodsServiceImpl
 * @Description: TODO
 * @Author wyj
 * @Date 2021/1/5
 * @Version V1.0
 **/
@RestController
public class GoodsServiceImpl extends BaseApiService implements GoodsService {

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private BrandMapper brandMapper;

    @Override
    public Result<List<SpuDTO>> getSupInfo(SpuDTO spuDTO) {
        // 分页
        if(ObjectUtil.isNotNull(spuDTO.getPage()) && ObjectUtil.isNotNull(spuDTO.getRows()))
            PageHelper.startPage(spuDTO.getPage(),spuDTO.getRows());

        Example example = new Example(SpuEntity.class);
        Example.Criteria criteria = example.createCriteria();

        // 上架和下架
        if(ObjectUtil.isNotNull(spuDTO.getSaleable()) && spuDTO.getSaleable() < 2)
            criteria.andEqualTo("saleable",spuDTO.getSaleable());

        //搜索 模糊查询
        if(!StringUtils.isEmpty(spuDTO.getTitle()))
            criteria.andLike("title", "%"+spuDTO.getTitle() +"%");

        List<SpuEntity> spuEntities = spuMapper.selectByExample(example);

        List<SpuDTO> spuDTOList  = spuEntities.stream().map(spuEntity -> {
            SpuDTO spuDTO1 = BaiduBeanUtil.copyProperties(spuEntity, SpuDTO.class);
             /* // 第一种
            CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(spuEntity.getCid1());
            CategoryEntity categoryEntity2 = categoryMapper.selectByPrimaryKey(spuEntity.getCid2());
            CategoryEntity categoryEntity3 = categoryMapper.selectByPrimaryKey(spuEntity.getCid3());
            spuDTO1.setCategoryName(categoryEntity.getName() + "/" + categoryEntity2.getName() + "/" + categoryEntity3.getName());
            */

              /*  第二种
            List<CategoryEntity> categoryEntities = categoryMapper.selectByIdList(Arrays.asList(spuEntity.getCid1(), spuEntity.getCid2(), spuEntity.getCid3()));
            String categoryName = "";
            List<String> categoryNames = new ArrayList<>();
            categoryNames.add(0,"");
            categoryEntities.stream().forEach(categoryEntity -> {
                categoryNames.set(0,categoryNames.get(0) + categoryEntity.getName() + "/");
            });
            categoryName =  categoryNames.get(0).substring(0,categoryNames.get(0).length());
            spuDTO1.setCategoryName(categoryName); */


            // 第三种
            List<CategoryEntity> categoryEntities = categoryMapper.selectByIdList(Arrays.asList(spuEntity.getCid1(), spuEntity.getCid2(), spuEntity.getCid3()));

            String categoryName = categoryEntities.stream().map(categoryEntity -> categoryEntity.getName()).collect(Collectors.joining("/"));
            spuDTO1.setCategoryName(categoryName);

            //品牌名称
            BrandEntity brandEntity = brandMapper.selectByPrimaryKey(spuEntity.getBrandId());
            spuDTO1.setBrandName(brandEntity.getName());

            return spuDTO1;
        }).collect(Collectors.toList());


        PageInfo<SpuEntity> spuEntityPageInfo = new PageInfo<>(spuEntities);
      //  return this.setResultSuccess(spuEntityPageInfo);
        return this.setResult(HTTPStatus.OK,spuEntityPageInfo.getTotal() + "" ,spuDTOList);
    }

}
