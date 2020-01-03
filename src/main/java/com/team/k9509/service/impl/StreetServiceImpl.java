package com.team.k9509.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.Street;
import com.team.k9509.entity.StreetExample;
import com.team.k9509.mapper.StreetMapper;
import com.team.k9509.mapper.StreetMapper;
import com.team.k9509.service.StreetService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper StreetMapper;



    @Override
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PageInfo findStreetAllByPB(PageUtil pageUtil) {

        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询数据
        StreetExample StreetExample = new StreetExample();
        List<Street> list = StreetMapper.selectByExample(StreetExample);
        //封装数据
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    @Override
    public Integer addStreet(Street Street) {
        return StreetMapper.insertSelective(Street);
    }

    @Override
    public Street findStreetById(Integer id) {
        return StreetMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateStreet(Street Street) {

        return StreetMapper.updateByPrimaryKeySelective(Street);
    }


    @Override
    @Transactional
    public void deleteStreet(Integer id) {

         StreetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer removeMoreStreet(Integer[] ids) {
        return StreetMapper.deleteMoreStreet(ids);
    }

    @Override
    public List<Street> findStreetByDid(Integer did) {
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        List<Street> streetList = StreetMapper.selectByExample(streetExample);
        return streetList;
    }
}
