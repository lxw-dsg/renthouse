package com.team.k9509.pcontroller;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.House;
import com.team.k9509.entity.Users;
import com.team.k9509.service.HouseService;
import com.team.k9509.utils.Condition;
import com.team.k9509.utils.FileUploadUtil;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("addHouse")
    public String addHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session){



           try{
               //上传图片
               String saveFileName = FileUploadUtil.upload(pfile);


               //将数据添加到数据库  。。。提交房源信息
               //设置编号
               house.setId(System.currentTimeMillis()+"");
               //设置所属用户   根据登录保存在session中的用户数据loginInfo调取id
               Users users = (Users) session.getAttribute("loginInfo");
               house.setUserId(users.getId());
               //设置图片路径
               house.setPath(saveFileName);
               //设置房屋信息是否删除
               house.setIsdel(0);
               house.setIspass(0);
               //调用业务实现添加
               houseService.addHouse(house);
               return "redirect:showHouse";
           }catch (Exception e){
               return "error";
           }



    }

    /**
     * 分页显示用户发布信息
     * 返回页面
     * PageUtils只用接收页面数
     */

    @RequestMapping("showHouse")
    public String showHouse(PageUtil pageUtil, HttpSession session, Model model){
        //设置页大小  设置默认值
        pageUtil.setRows(5);
        if (pageUtil.getPage()==null){
            pageUtil.setPage(1);
        }
        //获取用户登录id
        Users user = (Users) session.getAttribute("loginInfo");
        PageInfo pageInfo = houseService.findHouseByUser(pageUtil, user.getId());
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    //显示修改
    @RequestMapping("updateHouseUI")
    public String updateHouseUI(String id,Model model){
        House house = houseService.getHouseById(id);
        model.addAttribute("house",house);
        return "upfabu";
    }

    //修改
    @RequestMapping("updateHouse")
    public String updateHouse(House house,String oldPath,@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){

       try{
           //先判断，有没有选择图片上传
           if (!pfile.getOriginalFilename().equals("")){
               //上传图片
               String saveFileName = FileUploadUtil.upload(pfile);
               //修改图片路径
               house.setPath(saveFileName);
               //删除旧图
               FileUploadUtil.deleteFile(oldPath);
           }

           //修改数据库信息
           houseService.updateHouse(house);
           return "redirect:showHouse";
       }catch (Exception e){
           return "error";
       }
    }

    //逻辑删除出租房
    @RequestMapping("deleteHouse")
    public String deleteHouse(String hid){

        try{

            Integer i = houseService.deleteHouse(hid, 1);
            return "redirect:showHouse";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //逻辑恢复出租房
    @RequestMapping("resumeHouse")
    public String resumeHouse(String hid){

        try{

            Integer i = houseService.deleteHouse(hid, 0);
            return "redirect:showHouse";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //用户浏览所有房源信息
    @RequestMapping("browseHouse")
    public String browseHouse(Model model, Condition condition){

        //设置页大小
        condition.setRows(5);
        //当没有上传页面数时，令页数为1
        if (condition.getPage()==null){
            condition.setPage(1);
        }
        //调用业务
        PageInfo pageInfo = houseService.findBrowseHouse(condition);
        //填充模板
        model.addAttribute("pageInfo",pageInfo);
        //回显
        model.addAttribute("condition",condition);
        return "list";
    }
}
