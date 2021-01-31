package cn.author.fwwd.service.impl;

import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.author.fwwd.Utils.DateUtils;

import java.io.File;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private PropertiesConfig config;
    public static final String UPLOAD_DIR ="UPLOAD_DIR";
    @Override
    public Attach selectByPrimaryKey(Long id){
        if(null==id||id==0){
            return null;
        }
       return attachMapper.selectByPrimaryKey(id);
    }
    @Override
    public Long saveFile2Disk(MultipartFile multifile, String fid, String category) throws Exception {
        Attach queryParams = new Attach();
        queryParams.setCategory(category);
//        queryParams.setModuleId(moduleId);
//        queryParams.setFid(todoId);
        String suffix = multifile.getOriginalFilename().substring(multifile.getOriginalFilename().lastIndexOf(".")+1);
        if(StringUtils.isBlank(suffix)){
            throw new RuntimeException("附件格式非法，文件名称需带后缀，请重命名！");
        }
        String dir = "D:\\upload";
        Long fileId = DateUtils.getSerialId(config.getServerId(), ServiceID.FILE.getCode());
        String fileName = fileId+"."+suffix;
        String fileUrl = this.saveFile2DiskWithNameDir(multifile, fileName, dir);


        Attach attach = queryParams;
        attach.setId(fileId);
//        attach.setFid(todoId);
        attach.setHost("192.168.1.8:8080");
        attach.setCategory(category);
        attach.setUrl(fileUrl);
        byte status = 1;
        attach.setStatus(status);
        attach.setFileName(multifile.getOriginalFilename());
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
