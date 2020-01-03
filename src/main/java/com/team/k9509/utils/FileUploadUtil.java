package com.team.k9509.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public class FileUploadUtil {

    private static final String savePosition = "e:\\image\\";


    public static String upload(CommonsMultipartFile pfile){

        try{
            //获取文件扩展名
            String pfname = pfile.getOriginalFilename();
            //获取文件后缀。。。文件类型
            String fixpname = pfname.substring(pfname.lastIndexOf("."));
            //生成新文件名
            String unique = System.currentTimeMillis()+"";
            String npname = unique+fixpname;
            //设置文件保存路径
            String savePath ="E:\\image\\"+npname;
            File file = new File(savePath);
            pfile.transferTo(file);
            return npname;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String filename){

        File file = new File(savePosition+filename);
        return file.delete();
    }
}
