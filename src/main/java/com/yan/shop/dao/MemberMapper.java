package com.yan.shop.dao;

import com.yan.shop.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int insert(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    Member selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    List<Member> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Member record);

    List<Member> selectByMember(@Param("member") Member member);

    int updateByEmail(String email);
}