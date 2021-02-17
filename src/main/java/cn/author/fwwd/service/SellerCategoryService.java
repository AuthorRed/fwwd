package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.SellerCategory;

import java.util.List;

public interface SellerCategoryService {

    List<String> list( String sellerUid);

    int countSellerCategoryDuplicate(String sellerUid,String categroy);

    int saveSellerCategory(SellerCategory record);
}
