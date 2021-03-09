package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    private static Logger logger;

    private Logging() {}
    public static void log(Throwable e, Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
        logger.error(e.getMessage(), e);
    }
}
