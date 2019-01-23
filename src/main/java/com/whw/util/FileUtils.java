package com.whw.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 张芳容 on 2017/07/12.
 */
public class FileUtils {
    /**
     * 返回图片名称
     *
     * @return
     */
    public static boolean savePic(MultipartFile multipartFile,String path) {
        try {
            File fileNew = new File(path);
            multipartFile.transferTo(fileNew);
            fileNew.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
