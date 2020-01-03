package com.team.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.District;
import com.team.k9509.service.DistrictService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @RequestMapping("/getdistrict")
    public Map<String,Object> getdistrict(PageUtil pageUtil){

        PageInfo pageInfo = districtService.findDistrictAllByPB(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addDistrict")
    public String addDistrict(District district){

        try {
            Integer i = districtService.addDistrict(district);
            return "{\"result\":"+i+"}";

        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    @RequestMapping("/showDistrict")
    public District showDistrict(Integer id){

        try{
            District district = districtService.findDistrictById(id);
            return district;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/updateDistrict")
    public String updateDistrict(District district){

        try{
            Integer i = districtService.updateDistrict(district);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    @RequestMapping("/deleteDistrict")
    public String deleteDistrict(Integer id){
        try{
           districtService.deleteDistrict(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    //批量删除
    @RequestMapping("/removeMoreDistrict")
    public String removeMoreDistrict(String ids){
        try{

            //将字符串转化为数组
            String[] split = ids.split(",");
            Integer[]idList = new Integer[split.length];
            for (int i = 0; i <split.length ; i++) {
                idList[i] = new Integer(split[i]);
            }
            Integer i = districtService.removeMoreDistrict(idList);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

}
