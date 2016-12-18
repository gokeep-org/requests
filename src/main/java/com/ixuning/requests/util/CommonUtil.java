package com.ixuning.requests.util;

import java.util.Objects;

/**
 * Created by xuning on 2016/8/11.
 * 通用
 */
public final class CommonUtil {
    private CommonUtil() {
    }

    /**
     * 验证对象是不是有效值
     *
     * @param objects
     * @return
     */
    public static boolean checkObjectsInvoke(Object... objects) {
        if (objects.length <= 0) {
            return false;
        }
        for (Object o : objects) {
            if (Objects.equals(o, null) || Objects.equals(o, "") || null == o) {
                return false;
            }
        }
        return true;
    }
}
