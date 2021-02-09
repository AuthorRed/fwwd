package cn.author.fwwd.service;

import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.dao.model.Commodity;

import java.util.List;
import java.util.Map;

public interface SearchService {
    public List<Map<String, Object>> homePageSearch(String keyWord, PageBean pageBean) throws Exception;

    public void addCommodity2ES(Commodity commodity);
}
