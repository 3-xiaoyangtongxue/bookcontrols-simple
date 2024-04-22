package com.java1234.util;

public class stringutil {
    public static boolean isempty(String str){
        if(str==null||"".equals(str.trim())){
            return  true;
        }else{
            return  false;
        }
    }
    public  static boolean isnotempty(String str){
        if(str!=null&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
}
