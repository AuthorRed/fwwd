package cn.author.fwwd.service.impl;

import cn.author.fwwd.Utils.HashUtils;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.mapper.AttachMapper;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.dao.model.User;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.FileService;
import cn.author.fwwd.service.TokenService;
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
    @Autowired
    private TokenService tokenService;
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
    public Long saveFile2Disk(MultipartFile multifile,Attach attach) throws Exception {
        if(StringUtils.isBlank(attach.getToken())){
            throw new RuntimeException("会话过期，请重新登录！");
        }
        User loginUser = tokenService.getLoginUser(attach.getToken());
        if(null ==loginUser){
            throw new RuntimeException("会话过期，请重新登录！");
        }
        attach.setUploadUid(loginUser.getUid());
        attach.setFidHash(HashUtils.getIntHash(String.valueOf(attach.getFid()),ServiceID.FILE));
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
        attach.setUrl(fileUrl);
        byte status = 1;
        attach.setStatus(status);
        attach.setFileName(filename);
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
