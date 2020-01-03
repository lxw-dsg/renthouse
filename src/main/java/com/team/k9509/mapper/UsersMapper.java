package com.team.k9509.mapper;

import com.team.k9509.entity.Users;
import com.team.k9509.entity.UsersExample;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    //批量删除
    Integer deleteMoreUsers(Integer[] ids);
}