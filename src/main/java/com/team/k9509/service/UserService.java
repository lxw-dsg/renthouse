package com.team.k9509.service;

import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Users;
import com.team.k9509.entity.Users;
import com.team.k9509.utils.PageUtil;

public interface UserService {

    /**
     *
     * @param users 继承PageUtil
     * @return
     */
    //分页显示
    PageInfo findUsersAllByPB(Users users);

    //添加业务
    Integer addUsers(Users users);

    //添加回显
    Users findUsersById(Integer id);

    //修改
    Integer updateUsers(Users users);

    //删除Users
    void deleteUsers(Integer id);

    //批量删除
    Integer removeMoreUsers(Integer[] ids);

    //判断用户是否存在
    boolean checkUname(String name);

    //用户的登录

    /**
     *
     * @param username 用户名
     * @param password  密码
     * @return
     */
    Users login(String username,String password);




}
