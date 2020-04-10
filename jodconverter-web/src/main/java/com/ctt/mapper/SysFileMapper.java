package com.ctt.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @auther Administrator
 * @create 2020-03-06 下午 2:16
 */
public interface SysFileMapper {

    public List<JSONObject> getFileList(@Param("fileKey")String fileKey,@Param("filePkey")String filePkey);

    public void saveFile(@Param("json") JSONObject json);

    JSONObject getFileByID(@Param("fileKey") String fileKey);
}
