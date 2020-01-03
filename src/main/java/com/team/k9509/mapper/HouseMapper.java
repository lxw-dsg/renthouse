package com.team.k9509.mapper;

import com.team.k9509.entity.House;
import com.team.k9509.entity.HouseExample;
import com.team.k9509.utils.Condition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户发布的出租房
    List<House> selectHouseByUser(Integer userid);

    // 回显==》查询单条记录
    House getHouseById(String id);

    //查询所有出租房信息
    List<House> selectAllHouse();

    //用户浏览所有房源信息
    List<House> getBrowseHouse(Condition condition);
}