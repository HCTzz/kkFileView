package com.ctt.service;

import com.ctt.model.FileAttribute;
import com.ctt.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by kl on 2018/1/17.
 * Content :
 */
@Service
public class FilePreviewFactory {

    @Autowired
    FileUtils fileUtils;

    @Autowired
    ApplicationContext context;

    public FilePreview get(FileAttribute fileAttribute) {
        Map<String, FilePreview> filePreviewMap = context.getBeansOfType(FilePreview.class);
        return filePreviewMap.get(fileAttribute.getType().getInstanceName());
    }
}
