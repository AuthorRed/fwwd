package cn.author.fwwd.controller;

import cn.author.fwwd.Utils.FileUtils;
import cn.author.fwwd.dao.mapper.Order1Mapper;
import cn.author.fwwd.dao.model.Order1;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("file")
//@CrossOrigin(origins = {"http://localhost:8000"}, maxAge = 100)
//@CrossOrigin
public class FileController {
    @Autowired
    private Order1Mapper order1Mapper;
    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping("test")
    public String test(){
        Order1 order1 = order1Mapper.selectByPrimaryKey(1);

        return JSON.toJSONString(order1);
    }
    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("fileUpload")
    public String upload(@RequestParam("file") MultipartFile file){
        // 要上传的目标文件存放路径
        String localPath = "E:\\upload";
        String fullPath = localPath + UUID.randomUUID();
        System.out.println(file.getOriginalFilename());
//        for (MultipartFile file : files) {
//            String originalFilename = file.getOriginalFilename();
//
//            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//            FileUtils.upload(file,)
//        }
//        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
//            // 上传成功，给出页面提示
//            msg = "上传成功！";
//        }

        return "forward:/test";
    }
}
