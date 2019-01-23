package com.whw.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 忘尘无憾 on 2017/07/05.
 */
public class ResultUtil {
    public static String BackResult(boolean flag, Object data, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag + "");
        map.put("msg", msg);
        map.put("data", data);
        return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect).toString();
    }

    public static String BackResult(boolean flag, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("msg", "");
        map.put("data", data);
        return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect).toString();
    }

    public static String BackResult(boolean flag) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("msg", "");
        map.put("data", "");
        return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect).toString();
    }

    public static String BackResult(boolean flag, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("msg", msg);
        map.put("data", "");
        return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect).toString();
    }
}
