package cn.author.fwwd.service.impl;

import cn.author.fwwd.dao.mapper.FileMapper;
import cn.author.fwwd.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.File;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    public static final String UPLOAD_DIR ="UPLOAD_DIR";

    @Override
    public byte[] queryFileById(String id) {

//        try {
//            if(StringUtils.isNotEmpty(id)){
//                String fullPath = daoMapper.getConfigMapper().selectValueByConfigKey(UPLOAD_DIR);
//                String fullName = fullPath +File.separatorChar+ id;
//                return FileUtils.readFileToByteArray(new File(fullName));
//            }
//        } catch (Throwable e) {
//            log.error("文件读取失败！",e);
//        }
//
        return null;
    }
    @Override
    public String saveFile2Disk(MultipartFile multifile, String fid, String moduleId, String category) throws Throwable {
//        Attach queryParams = new Attach();
//        queryParams.setCategory(category);
//        queryParams.setModuleId(moduleId);
//        queryParams.setFid(todoId);
//        List<Attach> queryResult = daoMapper.getAttachMapper().selectByAttach(queryParams);
//        if(CollectionUtil.isNotEmpty(queryResult)){
//            daoMapper.getAttachMapper().deleteByFid(todoId);
//        }
//
//        String suffix = multifile.getOriginalFilename().substring(multifile.getOriginalFilename().lastIndexOf(".")+1);
//        if(StringUtils.isBlank(suffix)){
//            throw new RuntimeException("附件格式非法，文件名称需带后缀，请重命名！");
//        }
//        String dir = "D:\\upload";
//        String fileId = DateUtils.getSerialId("file_"+moduleId+"_");
//        String fileName = fileId+"."+suffix;
//        String fileUrl = this.saveFile2DiskWithNameDir(multifile, fileName, dir);
//
//
//        Attach attach = queryParams;
//        attach.setId(fileId);
//        attach.setFid(todoId);
//        attach.setCategory(category);
//        attach.setSuffix(suffix);
//        attach.setModuleId(moduleId);
//        attach.setAttachUrl(fileUrl);
//        attach.setStatus(Constants.ISVALID_Y);
//        attach.setAttachName(multifile.getOriginalFilename());
//        attach.setUploadTime(new Date());
//        attach.setUploadBy(user.getUid());
//        daoMapper.getAttachMapper().insertSelective(attach);

        return null;
    }
    @Override
    public void deleteByPrimaryKey(String id) throws Throwable {
        if(StringUtils.isBlank(id)){
            throw new RuntimeException("要删除的Id参数不能为空！");
        }
        fileMapper.deleteByPrimaryKey(1l);
    }

    private String saveFile2DiskWithNameDir(MultipartFile multi, String fileName, String dir) throws Throwable {
        if(null!=multi && multi.getSize()>0){
            String fullName = dir+ File.separatorChar+fileName;
            File file = new File(fullName);
            if(file.exists()){
                file.delete();
            }
            multi.transferTo(file);
            return fullName;
        }
        return null;
    }
}
