package cn.author.fwwd.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String saveFile2Disk(MultipartFile uploadFile, String fid, String moduleId, String category) throws Throwable;

    byte[] queryFileById(String id);

    public void deleteByPrimaryKey(String id) throws Throwable;

}
