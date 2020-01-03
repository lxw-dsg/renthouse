package com.team.k9509.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Type;
import com.team.k9509.entity.TypeExample;
import com.team.k9509.mapper.TypeMapper;
import com.team.k9509.mapper.StreetMapper;
import com.team.k9509.service.TypeService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;



    @Override
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PageInfo findTypeAllByPB(PageUtil pageUtil) {

        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询数据
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        //封装数据
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    @Override
    public Integer addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type findTypeById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateType(Type type) {

        return typeMapper.updateByPrimaryKeySelective(type);
    }


    @Override
    @Transactional
    public void deleteType(Integer id) {

        typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer removeMoreType(Integer[] ids) {
        return typeMapper.deleteMoreType(ids);
    }

    @Override
    public List<Type> findTypeAll() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
