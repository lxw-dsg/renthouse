package com.team.k9509.service;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Type;
import com.team.k9509.utils.PageUtil;

import java.util.List;

public interface TypeService {

    /**
     *
     * @param pageUtil  工具类，封装 page当前页  rows每页记录数
     * @return
     */
    //分页显示
    PageInfo findTypeAllByPB(PageUtil pageUtil);

    //添加业务
    Integer addType(Type type);

    //添加回显
    Type findTypeById(Integer id);

    //修改
    Integer updateType(Type type);

    //删除Type
    void deleteType(Integer id);

    //批量删除
    Integer removeMoreType(Integer[] ids);

    //查询全部
    List<Type> findTypeAll();
}
