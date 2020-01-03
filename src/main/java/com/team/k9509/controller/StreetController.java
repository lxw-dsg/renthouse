package com.team.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Street;
import com.team.k9509.service.StreetService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class StreetController {

    @Autowired
    StreetService StreetService;

    @RequestMapping("/getStreet")
    public Map<String,Object> getStreet(PageUtil pageUtil){

        PageInfo pageInfo = StreetService.findStreetAllByPB(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addStreet")
    public String addStreet(Street Street){

        try {
            Integer i = StreetService.addStreet(Street);
            return "{\"result\":"+i+"}";

        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    @RequestMapping("/showStreet")
    public Street showStreet(Integer id){

        try{
            Street street = StreetService.findStreetById(id);
            return street;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/updateStreet")
    public String updateStreet(Street Street){

        try{
            Integer i = StreetService.updateStreet(Street);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    @RequestMapping("/deleteStreet")
    public String deleteStreet(Integer id){
        try{
           StreetService.deleteStreet(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    //批量删除
    @RequestMapping("/removeMoreStreet")
    public String removeMoreStreet(String ids){
        try{

            //将字符串转化为数组
            String[] split = ids.split(",");
            Integer[]idList = new Integer[split.length];
            for (int i = 0; i <split.length ; i++) {
                idList[i] = new Integer(split[i]);
            }
            Integer i = StreetService.removeMoreStreet(idList);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

}
