package com.team.k9509.pcontroller;


import com.team.k9509.entity.Users;
import com.team.k9509.service.UserService;
import com.team.k9509.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("userController2")
@RequestMapping("/page/")
public class UserController {

    @Autowired
    private UserService userService;


    //用户名是否已存在
    @RequestMapping("checkUname")
    @ResponseBody
    public String checkUname(String uname){

        boolean b = userService.checkUname(uname);
        return "{\"result\":"+b+"}";

    }

    //注册
    @RequestMapping("reg")
    public String reg(Users users){
        Integer i = userService.addUsers(users);
        if (i>0){
            return "redirect:login.htm";
        }else {
            return "redirect:regs.htm";
        }
    }

    //登录
    @RequestMapping("userlogin")
    public String login(String username, String password, HttpSession session){


        Users users = userService.login(username, password);
        if (users==null){
            return "redirect:login.htm";
        }else {
            session.setAttribute("loginInfo",users);
            session.setMaxInactiveInterval(10*60);
            return "redirect:showHouse";
        }


//        boolean b = userService.checkUname(username);
//        if (!b){
//            Users users = userService.findUsersByUName(username);
//            String s = MD5Utils.md5Encrypt(password);
//           if (s.equals(users.getPassword())){
//               //用户名密码均正确 1
//               return "{\"result\":1}";
//           }else {
//               //密码错误 2
//               return "{\"result\":2}";
//           }
//        }else {
//            //用户名不存在 3
//            return "{\"result\":3}";
//        }
    }
}
