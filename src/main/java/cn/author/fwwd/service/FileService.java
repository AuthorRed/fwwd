package cn.author.fwwd.service;

import cn.author.fwwd.dao.model.Attach;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<Attach> selectByFid(Long id);

    Attach selectByPrimaryKey(Long id);

    Long saveFile2Disk(MultipartFile uploadFile,Attach attach) throws Exception;

    public void deleteByPrimaryKey(String id) throws Throwable;

}
