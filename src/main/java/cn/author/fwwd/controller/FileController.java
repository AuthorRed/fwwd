package cn.author.fwwd.controller;

import cn.author.fwwd.Utils.DateUtils;
import cn.author.fwwd.Utils.FileContentTypeUtils;
import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.config.PropertiesConfig;
import cn.author.fwwd.dao.model.Attach;
import cn.author.fwwd.enums.ServiceID;
import cn.author.fwwd.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RestController
@RequestMapping("file")
//@CrossOrigin(origins = {"http://localhost:8000"}, maxAge = 100)
//@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private PropertiesConfig config;
    /**
     * @param file 要上传的文件
     * @return
     */
    @PostMapping("fileUpload")
    public ResultMsg upload(@RequestParam("file") MultipartFile file,Long fid){
        // 要上传的目标文件存放路径
        try{
//            String localPath = "D:\\upload";
//            String fullPath = localPath + DateUtils.getSerialId(config.getServerId(), ServiceID.FILE.getCode());

            Long fileId = fileService.saveFile2Disk(file, fid, "image");
            ResultMsg resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("fileId",fileId);
            return resultMsg;
        }catch (Exception e){
            log.error("文件上传失败:"+e);
            return ResultMsg.error(e.getMessage());
        }
    }
    @GetMapping("getFileById")
    public void getFileById(Long id, HttpServletRequest request, HttpServletResponse response){
        try {
            Attach attach = fileService.selectByPrimaryKey(id);
            if(null==attach){
                return;
            }
            String fileName = attach.getFileName();
            String extention = fileName.substring(fileName.lastIndexOf("."));
            if(null==attach || StringUtils.isBlank(attach.getUrl())|| StringUtils.isBlank(fileName)|| StringUtils.isBlank(extention)){
                return;
            }
            File file = new File(attach.getUrl());
//            String l=request.getRealPath("/")+"/"+url;
//            String filename = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType(FileContentTypeUtils.getContentType(extention));

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
