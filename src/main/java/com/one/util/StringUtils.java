package com.one.util;


public class StringUtils {
    public static boolean isEmpty(String str){
        return str==null||str.equals("");
    }

    public static boolean isNotEmpty(String str){
        if(!"".equals(str)&&str!=null){
            return true;
        }else{
            return false;
        }
    }
}