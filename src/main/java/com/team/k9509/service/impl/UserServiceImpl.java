package com.team.k9509.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Users;
import com.team.k9509.entity.UsersExample;
import com.team.k9509.entity.Users;
import com.team.k9509.mapper.UsersMapper;
import com.team.k9509.mapper.StreetMapper;
import com.team.k9509.service.UserService;
import com.team.k9509.utils.MD5Utils;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PageInfo findUsersAllByPB(Users users) {

        PageHelper.startPage(users.getPage(),users.getRows());
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        if (users.getName()!=null){
            criteria.andNameLike("%"+users.getName()+"%");
        }
        if (users.getTelephone()!=null){
            criteria.andTelephoneLike("%"+users.getTelephone()+"%");
        }
        List<Users> list = usersMapper.selectByExample(usersExample);
        return new PageInfo<>(list);

    }

    @Override
    public Integer addUsers(Users users) {

        //密码加密存储
        String s = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(s);
        //设置用户为房东客户
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users findUsersById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateUsers(Users users) {

        return usersMapper.updateByPrimaryKeySelective(users);
    }


    @Override
    @Transactional
    public void deleteUsers(Integer id) {
        usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer removeMoreUsers(Integer[] ids) {
        return usersMapper.deleteMoreUsers(ids);
    }

    //用户名是否已存在
    @Override
    public boolean checkUname(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));//保证是房东用户
        criteria.andNameEqualTo(name);
        List<Users> usersList = usersMapper.selectByExample(usersExample);

        if (usersList!=null&&usersList.size()!=0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Users login(String username, String password) {

        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        criteria.andIsadminEqualTo(new Integer("0"));
        List<Users> usersList = usersMapper.selectByExample(usersExample);

        if (usersList!=null&&usersList.size()>0){

            return usersList.get(0);
        }
        return null;
    }


}
