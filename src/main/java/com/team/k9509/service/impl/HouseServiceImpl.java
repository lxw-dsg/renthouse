package com.team.k9509.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.House;
import com.team.k9509.mapper.HouseMapper;
import com.team.k9509.service.HouseService;
import com.team.k9509.utils.Condition;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Override
    public Integer addHouse(House house) {

        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo findHouseByUser(PageUtil pageUtil, Integer id) {

        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.selectHouseByUser(id);
        PageInfo pageInfo = new PageInfo(houseList);
        return pageInfo;
    }

    @Override
    public House getHouseById(String id) {
        return houseMapper.getHouseById(id);
    }

    @Override
    public Integer updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //逻辑更改，控制器删除、恢复都可以调用
    @Override
    public Integer deleteHouse(String hid, Integer delState) {
        House house = new House();
        house.setId(hid);
        house.setIsdel(delState);
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }

    @Override
    public PageInfo findAllHouse(PageUtil pageUtil) {

        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.selectAllHouse();
        PageInfo pageInfo = new PageInfo(houseList);
        return pageInfo;
    }

    @Override
    public Integer passHouse(String hid, Integer passState) {
        House house = new House();
        house.setId(hid);
        house.setIspass(passState);
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }

    @Override
    public PageInfo findBrowseHouse(Condition condition) {

        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> houseList = houseMapper.getBrowseHouse(condition);
        PageInfo pageInfo = new PageInfo(houseList);
        return pageInfo;
    }
}
