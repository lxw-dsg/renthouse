package com.team.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.District;
import com.team.k9509.entity.Users;
import com.team.k9509.service.DistrictService;
import com.team.k9509.service.UserService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UsersController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUsers")
    public Map<String,Object> getUsers(Users users){

        PageInfo pageInfo = userService.findUsersAllByPB(users);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }



}
