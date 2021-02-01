package cn.author.fwwd.service.impl;

import cn.author.fwwd.dao.mapper.CommodityMapper;
import cn.author.fwwd.dao.mapper.UserMapper;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.service.CommodityService;
import cn.author.fwwd.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    public int deleteByPrimaryKey(Long id){
       return commodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Commodity record){
        return commodityMapper.insertSelective(record);
    }

    @Override
    public Commodity selectByPrimaryKey(Long id){
        return commodityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Commodity record){
        return commodityMapper.updateByPrimaryKeySelective(record);
    }
}
