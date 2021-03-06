package com.yan.shop.dao;

import com.yan.shop.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated
     */
    int insert(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated
     */
    Goods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated
     */
    List<Goods> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Goods record);

    List<Goods> selectBy(@Param("sort") int sort, @Param("goods") Goods goods);

    int addSearchTimesByNameAndType(@Param("startName") String startName, @Param("type") int type);

    int addSearch(Long id);

    int addSale(Long id);
}