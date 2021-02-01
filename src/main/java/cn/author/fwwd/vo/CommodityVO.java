package cn.author.fwwd.vo;

import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Commodity;
import lombok.Data;


import java.util.ArrayList;
@Data
public class CommodityVO {
    private Commodity commodity;

    private ArrayList<Attach> attachList;


}
