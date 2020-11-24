package com.ego.manager.mapper;

import com.ego.manager.pojo.GoodsCategory;
import com.ego.manager.pojo.GoodsCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryMapper {
    long countByExample(GoodsCategoryExample example);

    int deleteByExample(GoodsCategoryExample example);

    int deleteByPrimaryKey(Short id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    List<GoodsCategory> selectByExample(GoodsCategoryExample example);

    GoodsCategory selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") GoodsCategory record, @Param("example") GoodsCategoryExample example);

    int updateByExample(@Param("record") GoodsCategory record, @Param("example") GoodsCategoryExample example);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);
}