package cn.author.fwwd.controller;

import cn.author.fwwd.common.ResultMsg;
import cn.author.fwwd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.author.fwwd.dao.model.User;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResultMsg login(String uid,String pwd){
        ResultMsg resultMsg = null;
        try {
            User user = userService.usernamePwdLogin(uid, pwd);
            if(null==user){
                return ResultMsg.error("用户名密码不正确!");
            }
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("user",user);
        }catch (Exception e){
            log.error("登录失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

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
            User register = userService.register(user);
            resultMsg = ResultMsg.success();
            resultMsg.getExtenal().put("user",register);
        }catch (Exception e){
            log.error("注册失败:",e);
            return ResultMsg.error(e.getMessage());
        }
        return resultMsg;
    }

}
