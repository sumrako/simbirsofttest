package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorHanding {
    private static Logger logger;

    public static void errorHanding(Throwable e, Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
        logger.error(e.getMessage(), e);
    }
}
