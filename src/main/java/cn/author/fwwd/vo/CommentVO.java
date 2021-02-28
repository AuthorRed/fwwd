package cn.author.fwwd.vo;

import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.Comment;
import lombok.Data;

import java.util.List;
@Data
public class CommentVO {
    private Comment comment;

    private List<Attach> attaches;

}
