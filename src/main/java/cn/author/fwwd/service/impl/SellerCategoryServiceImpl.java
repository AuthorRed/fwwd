package cn.author.fwwd.service.impl;

import cn.author.fwwd.dao.mapper.CommodityMapper;
import cn.author.fwwd.dao.mapper.SellerCategoryMapper;
import cn.author.fwwd.dao.model.SellerCategory;
import cn.author.fwwd.service.SellerCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SellerCategoryServiceImpl implements SellerCategoryService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;
    @Override
    public List<String> list( String sellerUid){
        if(StringUtils.isNotBlank(sellerUid)){
            return sellerCategoryMapper.list(sellerUid);
        }
        return null;
    }
    @Override
    public int countSellerCategoryDuplicate(String sellerUid,String categroy){
        if(StringUtils.isBlank(sellerUid) || StringUtils.isBlank(categroy)){
            throw new RuntimeException("查询参数不能为空!");
        }
        return sellerCategoryMapper.countSellerCategoryDuplicate(sellerUid,categroy);
    }
    @Override
    public int saveSellerCategory(SellerCategory category){
        if(StringUtils.isNotBlank(category.getCategroy())){
            category.setCategroy(category.getCategroy().trim());
        }else{
            log.info("该用户已经有此目录.");
            return 0;
        }
        int count = this.countSellerCategoryDuplicate(category.getSellerUid(), category.getCategroy());
        if(count>=1){
            log.info("该用户已经有此目录.");
            return 0;
        }
        return sellerCategoryMapper.insertSelective(category);
    }

}
