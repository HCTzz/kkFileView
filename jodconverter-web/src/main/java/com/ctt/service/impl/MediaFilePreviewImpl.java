package com.ctt.service.impl;

import com.ctt.model.FileAttribute;
import com.ctt.service.FilePreview;
import com.ctt.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
/**
 * @author : kl
 * @authorboke : kailing.pub
 * @create : 2018-03-25 上午11:58
 * @description:
 **/
@Service
public class MediaFilePreviewImpl implements FilePreview {

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        model.addAttribute("mediaUrl", url);
        String suffix=fileAttribute.getSuffix();
        if ("flv".equalsIgnoreCase(suffix)) {
            return "flv";
        }
        return "media";
    }


}
