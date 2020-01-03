package com.team.k9509.controller;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Type;
import com.team.k9509.service.TypeService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService TypeService;

    @RequestMapping("/getType")
    public Map<String,Object> getType(PageUtil pageUtil){

        PageInfo pageInfo = TypeService.findTypeAllByPB(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addType")
    public String addType(Type Type){

        try {
            Integer i = TypeService.addType(Type);
            return "{\"result\":"+i+"}";

        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    @RequestMapping("/showType")
    public Type showType(Integer id){

        try{
            Type Type = TypeService.findTypeById(id);
            return Type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/updateType")
    public String updateType(Type Type){

        try{
            Integer i = TypeService.updateType(Type);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    @RequestMapping("/deleteType")
    public String deleteType(Integer id){
        try{
           TypeService.deleteType(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

    //批量删除
    @RequestMapping("/removeMoreType")
    public String removeMoreType(String ids){
        try{

            //将字符串转化为数组
            String[] split = ids.split(",");
            Integer[]idList = new Integer[split.length];
            for (int i = 0; i <split.length ; i++) {
                idList[i] = new Integer(split[i]);
            }
            Integer i = TypeService.removeMoreType(idList);
            return "{\"result\":"+i+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }

    }

}
