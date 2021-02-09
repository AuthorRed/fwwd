package cn.author.fwwd.service.impl;

import cn.author.fwwd.common.PageBean;
import cn.author.fwwd.dao.mapper.AttachMapper;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Commodity;
import cn.author.fwwd.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private AttachMapper attachMapper;
    @Override
    public List<Map<String, Object>> homePageSearch(String keyWord, PageBean pageBean) throws Exception {
        SearchRequest searchRequest = new SearchRequest("commodity");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new MatchQueryBuilder("title", keyWord));
        searchSourceBuilder.from(pageBean.getOffSet());
        searchSourceBuilder.size(pageBean.getRows());
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        ArrayList<Map<String, Object>> list = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }

    @Override
    public void addCommodity2ES(Commodity commodity){
        try {
            Attach attach = attachMapper.selectFirstOneByFid(commodity.getId());
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("id", commodity.getId());
            jsonMap.put("title", commodity.getTitle());
//            jsonMap.put("img_url", "kimchy");
            jsonMap.put("img_id", null!=attach?attach.getId():null);
            jsonMap.put("seller", commodity.getSeller());
            jsonMap.put("price", commodity.getPrice());
            IndexRequest indexRequest = new IndexRequest("commodity")
                    .id(String.valueOf(commodity.getId())).source(jsonMap);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if(indexResponse.getResult() != DocWriteResponse.Result.CREATED){
                throw new RuntimeException("添加失败");
            }
        }catch (Exception e){
            log.error("商品加入ES报错",e);
        }
    }
}
