package com.team.k9509.pcontroller;

import com.team.k9509.entity.Type;
import com.team.k9509.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("typeController2")
@RequestMapping("/page/")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("getTypeData")
    @ResponseBody
    public List<Type> getTypeData(){
        List<Type> typeList = typeService.findTypeAll();
        return typeList;
    }

}
