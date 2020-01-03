package com.team.k9509.mapper;

import com.team.k9509.entity.Type;
import com.team.k9509.entity.TypeExample;
import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    //批量删除
    Integer deleteMoreType(Integer[] ids);
}