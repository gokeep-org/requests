package com.ixuning.requests.util;

import java.util.Properties;

/**
 * Created by xuning on 2016/8/17.
 */
public final class PropertiesUtil {

    private static final PropertiesUtil propertiesUtil = new PropertiesUtil();
    private static final Properties properties = System.getProperties();

    private PropertiesUtil() {
    }

    public static final PropertiesUtil getPropertiesUtil() {
        return propertiesUtil;
    }

    public static final boolean isLinux() {
        String osName = properties.get("os.name").toString();
        return !(osName.startsWith("Win") || osName.startsWith("win"));
    }

}
