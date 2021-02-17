package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.HashUtils;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.AttachMapper;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.author.fwwd.Utils.DateUtils;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private PropertiesConfig config;
    public static final String UPLOAD_DIR ="UPLOAD_DIR";

    @Override
    public List<Attach> selectByFid(Long id){
        return attachMapper.selectByFid(id);
    }
    @Override
    public Attach selectByPrimaryKey(Long id){
        if(null==id||id==0){
            return null;
        }
       return attachMapper.selectByPrimaryKey(id);
    }
    @Override
    public Long saveFile2Disk(MultipartFile multifile, Long fid, String category) throws Exception {
        Attach attach = new Attach();
        attach.setCategory(category);
        attach.setFid(fid);
        attach.setFidHash(HashUtils.getIntHash(String.valueOf(fid),ServiceID.FILE));
        String suffix = multifile.getOriginalFilename().substring(multifile.getOriginalFilename().lastIndexOf(".")+1);
        if(StringUtils.isBlank(suffix)){
            throw new RuntimeException("附件格式非法，文件名称需带后缀，请重命名！");
        }
        String filename = multifile.getOriginalFilename();
        if(filename.length()>30){
            throw new RuntimeException("文件名长度超过30，请重命名！");
        }
        String dir = "D:\\upload";
        Long fileId = DateUtils.getSerialId(config.getServerId(), ServiceID.FILE.getCode());
        String fileName = fileId+"."+suffix;
        String fileUrl = this.saveFile2DiskWithNameDir(multifile, fileName, dir);

        attach.setId(fileId);
        attach.setHost("192.168.1.8:8080");
        attach.setCategory(category);
        attach.setUrl(fileUrl);
        byte status = 1;
        attach.setStatus(status);
        attach.setFileName(filename);
//        attach.setUploadBy(user.getUid());
        attachMapper.insertSelective(attach);
        return fileId;
    }
    @Override
    public void deleteByPrimaryKey(String id) throws Throwable {
        if(StringUtils.isBlank(id)){
            throw new RuntimeException("要删除的Id参数不能为空！");
        }
        attachMapper.deleteByPrimaryKey(1l);
    }

    private String saveFile2DiskWithNameDir(MultipartFile multi, String fileName, String dir) throws Exception {
        String fullName = null;
        if(null!=multi && multi.getSize()>0){
            fullName = dir+ File.separatorChar+fileName;
            File file = new File(fullName);
            if(file.exists()){
                file.delete();
            }
            multi.transferTo(file);
            return fullName;
        }
        return fullName;
    }
}
