package com.team.k9509.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.k9509.entity.District;
import com.team.k9509.entity.DistrictExample;
import com.team.k9509.mapper.DistrictMapper;
import com.team.k9509.mapper.StreetMapper;
import com.team.k9509.service.DistrictService;
import com.team.k9509.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("districtService")

public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;


    @Override
    //@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PageInfo findDistrictAllByPB(PageUtil pageUtil) {

        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询数据
        DistrictExample districtExample = new DistrictExample();
        List<District> list = districtMapper.selectByExample(districtExample);
        //封装数据
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    @Override
    public Integer addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District findDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateDistrict(District district) {

        return districtMapper.updateByPrimaryKeySelective(district);
    }


    @Override
    @Transactional
    public void deleteDistrict(Integer id) {

         streetMapper.delStreetByDid(id);
         districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer removeMoreDistrict(Integer[] ids) {
        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> findDistrictAll() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
