package com.team.k9509.pcontroller;

import com.team.k9509.entity.Street;
import com.team.k9509.entity.Type;
import com.team.k9509.service.StreetService;
import com.team.k9509.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("streetController2")
@RequestMapping("/page/")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreetData")
    @ResponseBody
    public List<Street> getStreetData(Integer did){
        List<Street> streetList = streetService.findStreetByDid(did);
        return streetList;
    }

}
