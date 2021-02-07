package cn.author.fwwd.vo;

import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Commodity;
import lombok.Data;




@Data
public class CommodityVO {
    private Commodity commodity;

    private Attach attach;

    public CommodityVO() {
    }

    public CommodityVO(Commodity commodity, Attach attach) {
        this.commodity = commodity;
        this.attach = attach;
    }
}
