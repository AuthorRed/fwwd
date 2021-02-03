package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.CommodityMapper;
import cn.author.fwwd.dao.mapper.UserMapper;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.enums.CommodityStatus;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.CommodityService;
import cn.author.fwwd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

@Service
@Slf4j
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private PropertiesConfig config;
    @Override
    public int insertInBatch(Integer num,Integer page){
        for (int j = 0; j < page; j++) {
            LinkedList<Commodity> list = new LinkedList<>();
            Long serialId = DateUtils.getSerialId(config.getServerId(), ServiceID.COMMODITY.getCode());
            for (int i = 0; i < num ; i++) {
                Date now = new Date();
                long id = serialId +i;
                Commodity commodity = new Commodity();
                commodity.setId(id);
                commodity.setStatus(CommodityStatus.ADDED.getCode());
                commodity.setPrice(new BigDecimal(String.valueOf(i)));
                commodity.setUpdateTime(now);
                commodity.setSeller("11");
                commodity.setTitle("苹果手机iphone X");
                commodity.setUnit("元");
                list.add(commodity);
            }
            int i = commodityMapper.insertInBatch(list);
            log.info(String.valueOf(i));
        }
        return 0;
    }
    @Override
    public int deleteByPrimaryKey(Long id){
       return commodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Commodity record){
//        Long id = DateUtils.getSerialId(config.getServerId(), ServiceID.COMMODITY.getCode());
//        record.setId(id);
        if(null==record.getId() ||0l==record.getId()){
            throw new RuntimeException("添加商品失败，请刷新页面重试!");
        }
        record.setUpdateTime(new Date());
        record.setStatus(CommodityStatus.ADDED.getCode());
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
