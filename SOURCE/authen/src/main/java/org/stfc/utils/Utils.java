/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.utils;

import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author viettx
 */
@Component
public class Utils {

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public String generateUnique() {
        String guid = UUID.randomUUID().toString();
        guid = guid.replace("-", "");
        return guid;
    }

    public boolean isEmpty(String str) {
        return str == null || "".equals(str);

    }

    public void formatLog(Logger logger, boolean isError, String... arg) {
        if (isError) {
            logger.error("[RES - {}][{}-{}][{}][{}]", arg[0], arg[1], arg[2], arg[3], arg[4]);
        } else {
            logger.debug("[RES - {}][{}-{}][{}][{}]", arg[0], arg[1], arg[2], arg[3], arg[4]);
        }
    }

    public boolean isNull(Object object) {
        return object == null;
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str);

    }

    public boolean isNull(Collection collection) {
        return collection == null;
    }

    public boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();

    }
}
