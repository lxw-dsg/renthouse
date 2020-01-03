package com.team.k9509.service;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Street;
import com.team.k9509.utils.PageUtil;

import java.util.List;

public interface StreetService {

    /**
     *
     * @param pageUtil  工具类，封装 page当前页  rows每页记录数
     * @return
     */
    //分页显示
    PageInfo findStreetAllByPB(PageUtil pageUtil);

    //添加业务
    Integer addStreet(Street Street);

    //添加回显
    Street findStreetById(Integer id);

    //修改
    Integer updateStreet(Street Street);

    //删除Street
    void deleteStreet(Integer id);

    //批量删除
    Integer removeMoreStreet(Integer[] ids);

    //根据district_id查询全部街道
    List<Street> findStreetByDid(Integer did);
}
