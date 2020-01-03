package com.team.k9509.mapper;

import com.team.k9509.entity.Street;
import com.team.k9509.entity.StreetExample;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    //根据District_Id删除street
    @Delete("delete from street where DISTRICT_ID = #{did}")
    Integer delStreetByDid(Integer did);

    //批量删除
    Integer deleteMoreStreet(Integer[] ids);
}