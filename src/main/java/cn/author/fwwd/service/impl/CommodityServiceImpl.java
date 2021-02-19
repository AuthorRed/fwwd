package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.AttachMapper;
import cn.author.fwwd.dao.mapper.CommodityMapper;
import cn.author.fwwd.dao.mapper.UserMapper;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.dao.model.SellerCategory;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.enums.CommodityStatus;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.CommodityService;
import cn.author.fwwd.service.SearchService;
import cn.author.fwwd.service.SellerCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
@Slf4j
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PropertiesConfig config;
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private SearchService searchService;
    @Autowired
    private SellerCategoryService SellerCategoryService;
    @Override
    public List<Commodity> pageList(Commodity commodity){
        if(null==commodity.getStatus()){
            commodity.setStatus(CommodityStatus.ADDED.getCode());
        }
        if(null==commodity || null==commodity.getSeller()){
            throw new RuntimeException("商品用户不能为空!");
        }
        if(null==commodity.getSellerId()){
            User user = userMapper.selectByUID(commodity.getSeller());
            commodity.setSellerId(user.getId());
        }

        //Integer count = commodityMapper.count(commodity);
        log.info("list start");
        List<Commodity> list = commodityMapper.list(commodity);
//        ArrayList<CommodityVO> commodityVOList = new ArrayList<>(list.size());
//
//        for (Commodity comm : list) {
//            Attach attach = attachMapper.selectFirstOneByFid(comm.getId());
//            CommodityVO commodityVO = new CommodityVO(comm, attach);
//            commodityVOList.add(commodityVO);
//        }
//        log.info("list end");
        return list;
    }
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
                commodity.setSellerId(20210126091134622l);
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
        if(null==record.getId() ||0l==record.getId()||null==record.getSeller()){
            throw new RuntimeException("添加商品失败，请先登陆后刷新页面重试!");
        }
        record.setUpdateTime(new Date());
        record.setStatus(CommodityStatus.ADDED.getCode());
        if(null==record.getSellerId()){
            User user = userMapper.selectByUID(record.getSeller());
            record.setSellerId(user.getId());
        }
        if(StringUtils.isNotBlank(record.getCategory())){
            record.setCategory(record.getCategory().trim());
            SellerCategory category = new SellerCategory();
            category.setId(DateUtils.getSerialId(config.getServerId(),ServiceID.SELLER_CATEGORY.getCode()));
            category.setCategroy(record.getCategory());
            category.setSellerId(record.getSellerId());
            category.setSellerUid(record.getSeller());
            SellerCategoryService.saveSellerCategory(category);
        }
        Attach attach = attachMapper.selectFirstOneByFid(record.getId());
        record.setHeadImg(attach.getId());
        searchService.addCommodity2ES(record);
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
