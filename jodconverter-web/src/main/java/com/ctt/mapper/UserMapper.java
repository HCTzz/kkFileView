package com.ctt.mapper;

import com.ctt.bean.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @auther Administrator
 * @create 2020-03-05 上午 10:50
 */
public interface UserMapper {

    JSONObject findUserByUserNameAndPwd(@Param("userName")String userName,@Param("pwd")String pwd);

    User getInfo(@Param("username")String username);

}
