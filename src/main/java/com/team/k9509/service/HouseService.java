package com.team.k9509.service;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.House;
import com.team.k9509.utils.Condition;
import com.team.k9509.utils.PageUtil;

public interface HouseService {

    /**
     *
     * @param house 出租房实体
     * @return
     */
    Integer addHouse(House house);

    /**
     *
     * @param pageUtil  分页工具类
     * @param id  用户id
     * @return
     */
    PageInfo findHouseByUser(PageUtil pageUtil,Integer id);


    /**
     * 查询单条数据用以修改页面的回显
     * @param id  house的id
     * @return  返回house实体
     */
    House getHouseById(String id);


    /**
     *
     * @param house 传实体 根据id修改
     * @return  影响行数
     */
    //修改业务
    Integer updateHouse(House house);


    /**
     * 逻辑删除，实际是修改isdel字段值
     * @param hid   出租房编号
     * @param delState  删除状态  1：删除 0：未删除
     * @return 返回影响行数
     */
    Integer deleteHouse(String hid,Integer delState);


    /**
     * 查询全部房源信息，后台显示
     * @param pageUtil   分页工具类，封装 页数、页大小
     * @return  返回出租房信息
     */
    PageInfo findAllHouse(PageUtil pageUtil);


    /**
     * 设置审核状态
     * @param hid 出租房编号
     * @param passState 审核状态 1：审核通过 0：未审核
     * @return 影响
     */
    Integer passHouse(String hid,Integer passState);


    //用户浏览所有房源信息
    PageInfo findBrowseHouse(Condition condition);

}
