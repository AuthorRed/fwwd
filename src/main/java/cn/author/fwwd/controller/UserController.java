package cn.author.fwwd.controller;

import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.service.UserService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import cn.author.fwwd.dao.model.User;
import java.util.UUID;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("countUID")
    public ResultMsg countUID(String uid){
        ResultMsg resultMsg = null;
        try {
            int i = userService.countUID(uid);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("countUID",i);
        }catch (Exception e){
            e.printStackTrace();
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

    @PostMapping("register")
    public ResultMsg register(User user){
        ResultMsg resultMsg = null;
        try {
            userService.register(user);
            resultMsg = ResultMsg.success();
        }catch (Exception e){
            e.printStackTrace();

            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

}
