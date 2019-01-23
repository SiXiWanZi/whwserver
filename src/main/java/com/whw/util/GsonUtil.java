package com.whw.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  desc: json字符串、对象转换工具类
 *  Created by 忘尘无憾 on 2017/10/22.
 *  version:
 * </pre>
 */

public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }

    /**
     * 将Object对象转换成json字符串
     *
     * @param object
     * @return
     */
    public static String ObToStr(Object object) {
        String str = null;
        if (gson != null) {
            str = gson.toJson(object);
        }
        return str;
    }

    /**
     * 将gson字符串转成泛型bean
     *
     * @param gsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T GsonToBean(String gsonStr, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonStr, cls);
        }
        return t;
    }

    /**
     * gson字符串转成list集合
     * @param gsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> GsonToList(String gsonStr,Class<T> cls){
        List<T> list=new ArrayList<>();
        JsonArray array=new JsonParser().parse(gsonStr).getAsJsonArray();
        if(gson!=null){
            for(final JsonElement elem:array){
                list.add(gson.fromJson(elem,cls));
            }
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成list中有ArrayList的
     *
     * @param gsonString
     * @return
     */
    public static <T> T GsonToArrayList(String gsonString,TypeToken<T> type) {
        //ArrayList<List<ToastUtil>> list = null;
        if (gson != null) {
            return gson.fromJson(gsonString,
                    type.getType());
        }
        return null;
        // return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
