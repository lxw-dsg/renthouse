package com.team.k9509.service;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.District;
import com.team.k9509.utils.PageUtil;

import java.util.List;

public interface DistrictService {

    /**
     *
     * @param pageUtil  工具类，封装 page当前页  rows每页记录数
     * @return
     */
    //分页显示
    PageInfo findDistrictAllByPB(PageUtil pageUtil);

    //添加业务
    Integer addDistrict(District district);

    //添加回显
    District findDistrictById(Integer id);

    //修改
    Integer updateDistrict(District district);

    //删除district
    void deleteDistrict(Integer id);

    //批量删除
    Integer removeMoreDistrict(Integer[] ids);

    //查询全部
    List<District> findDistrictAll();
}
