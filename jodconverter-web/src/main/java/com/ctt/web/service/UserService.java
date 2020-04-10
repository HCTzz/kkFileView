package com.ctt.web.service;

import com.ctt.constant.UserStatusEnum;
import com.ctt.mapper.UserMapper;
import com.ctt.response.WebResBean;
import com.ctt.utils.EncryptUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.annotation.Resource;

/**
 * @Description用户操作类
 * @auther Administrator
 * @create 2020-03-05 上午 11:00
 */
@Service
public class UserService {

@Resource
private UserMapper userMapper;

    public WebResBean login(String userName,String pwd) throws HttpRequestMethodNotSupportedException {
        JSONObject js = userMapper.findUserByUserNameAndPwd(userName,pwd);
        WebResBean rsb = new WebResBean();
        if(js == null){
            rsb.setMessage(UserStatusEnum.S_5.getMessage());
        }
        int status = js.getIntValue("status");
        if(status == UserStatusEnum.S_2.getStatus()){
            rsb.setMessage(UserStatusEnum.S_2.getMessage());
        }
        rsb.setData(EncryptUtil.aesEncrypt(userName + ":" + pwd + ":" + EncryptUtil.getKey()));
        return rsb;
        }

    public WebResBean getInfo(String token){
        WebResBean rsb = new WebResBean();
        rsb.setData(userMapper.getInfo(EncryptUtil.getUserName(token)));
        return rsb;
    }
}
