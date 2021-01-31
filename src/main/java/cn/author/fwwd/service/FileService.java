package cn.author.fwwd.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Attach selectByPrimaryKey(Long id);


    Long saveFile2Disk(MultipartFile uploadFile, String fid, String category) throws Exception;



    public void deleteByPrimaryKey(String id) throws Throwable;

}
