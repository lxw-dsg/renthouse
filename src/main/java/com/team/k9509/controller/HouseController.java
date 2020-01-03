package com.team.k9509.controller;


import com.github.pagehelper.PageInfo;
import com.team.k9509.service.HouseService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("houseController2")
@RequestMapping("/admin/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("getHouseAll")
    public Map<String,Object> getHouseAll(PageUtil pageUtil){

        PageInfo pageInfo = houseService.findAllHouse(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("updatePassState")
    public Map<String,Object> updatePassState(String hid,Integer state){

        Integer i = houseService.passHouse(hid, state);
        Map<String,Object> map = new HashMap<>();
        map.put("result",i);
        return map;
    }
}
