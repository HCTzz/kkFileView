package com.ctt.utils;

import com.ctt.config.ConfigConstants;
import com.ctt.service.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @auther: chenjh
 * @since: 2019/6/11 7:45
 */
@Component
@ConditionalOnExpression("'${cache.clean.enabled:false}'.equals('true')")
public class ShedulerClean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShedulerClean.class);

    @Autowired
    private CacheService cacheService;

    private String fileDir = ConfigConstants.getFileDir();

    //默认每晚3点执行一次
    @Scheduled(cron = "${cache.clean.cron:0 0 3 * * ?}")
    public void clean() {
        LOGGER.info("Cache clean start");
        cacheService.cleanCache();
        DeleteFileUtil.deleteDirectory(fileDir);
        LOGGER.info("Cache clean end");
    }
}