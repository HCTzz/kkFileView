package com.ctt.web.controller;

import com.ctt.constant.SystemStatusEnum;
import com.ctt.response.WebResBean;
import com.ctt.web.service.SysFileService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**文件管理模块
 * @Description
 * @auther Administrator
 * @create 2020-03-06 下午 1:45
 */
@Api(description = "用户操作接口")
@RestController
@RequestMapping("sysFile")
public class SystemFileController extends BaseController{

    @Autowired
    private SysFileService sysFileService;

    @ApiOperation(value = "获取otp", notes="通过手机号获取OTP验证码")
    @ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @GetMapping("filelist")
    public WebResBean filelist(String filePkey, String fileKey){
        List<JSONObject> list = sysFileService.getFileList(fileKey,filePkey);
        WebResBean wsb = WebResBean.createResBean(SystemStatusEnum.E_20000);
        wsb.setData(list);
        return wsb;
    }

    @PostMapping("addFolder")//String filePkey,String fileName
    public WebResBean addFolder(@RequestBody JSONObject json){
        WebResBean wsb = WebResBean.createResBean(SystemStatusEnum.E_20000);
        wsb.setData(sysFileService.addFolder(json.getString("filePkey"),json.getString("fileName")));
        return wsb;
    }

    @GetMapping("getFile")
    public WebResBean getFile(String fileKey){
        JSONObject json = sysFileService.getFileByID(fileKey);
        WebResBean wsb = WebResBean.createResBean(SystemStatusEnum.E_20000);
        wsb.setData(json);
        return wsb;
    }

    @RequestMapping("fileUpload")
    public WebResBean fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        JSONObject json = sysFileService.uploadFile(file);
        WebResBean wsb = WebResBean.createResBean(SystemStatusEnum.E_20000);
        wsb.setData(json);
        return wsb;
    }

    @DeleteMapping("deleteFile")
    public WebResBean deleteFile(String fileKey) throws IOException {
        sysFileService.deleteFile(fileKey);
        WebResBean wsb = WebResBean.createResBean(SystemStatusEnum.E_20000);
        return wsb;
    }

}
