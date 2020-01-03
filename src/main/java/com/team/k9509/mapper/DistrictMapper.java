package com.team.k9509.mapper;

import com.team.k9509.entity.District;
import com.team.k9509.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    //批量删除
    Integer deleteMoreDistrict(Integer[] ids);
}