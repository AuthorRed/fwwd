package cn.author.fwwd.controller;

import cn.author.fwwd.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("countUID")
    public String countUID(String uid){
        int i = userService.countUID(uid);

        return null;
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
