package com.team.k9509.pcontroller;


import com.team.k9509.entity.District;
import com.team.k9509.entity.Users;
import com.team.k9509.service.DistrictService;
import com.team.k9509.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller("districtController2")
@RequestMapping("/page/")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrictData")
    @ResponseBody
    public List<District> getDistrictData(){
        List<District> districtList = districtService.findDistrictAll();
        return districtList;
    }
}
