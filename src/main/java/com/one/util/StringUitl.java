package com.one.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUitl {
    /**
     *  判断一个字符串是否为空
     * @param str
     * @return
     */
    public static boolean IsNull(String str){
        if(str==null||"".equals(str)||"".equals(str.trim())){
            return true;
        }
        return false;
    }
    /**
     *  判断一个字符串是否为非空
     * @param str
     * @return
     */
    public static boolean IsNotNull(String str){
        if(str==null||"".equals(str)||"".equals(str.trim())){
            return false;
        }
        return true;
    }
    /**
     * 转换成小写
     *@author wangym
     *@date Aug 15, 2012 9:16:41 AM
     * @param src
     * @return
     */
    public static String tranStartCharToLower(String src)
    {
        if (src == null || src.equals(""))
        {

            return null;
        } else
        {
            char target = src.charAt(0);
            return src.replaceFirst((new StringBuilder(String.valueOf(target))).toString(), (new StringBuilder(String.valueOf(Character.toLowerCase(src.charAt(0))))).toString());
        }
    }
    /**
     * 去掉字符串两端的空格
     *@author wangym
     *@date Aug 15, 2012 9:13:47 AM
     * @param str
     * @return
     */
    public static String toTrim(String str)
    {
        if (str == null)
            return "";
        if (str.trim().equalsIgnoreCase("null"))
            return "";
        else
            return str.trim();
    }

    /**
     * 获取项目路径
     * @return
     */
    public static String getWebRootPath(){
        String filePath=new StringUitl().getClass().getResource("/").getPath();
        if(filePath.indexOf("/")==0){
            filePath=filePath.substring(1, filePath.lastIndexOf("WEB-INF"));
        }else{
            filePath=filePath.substring(0, filePath.lastIndexOf("WEB-INF"));
        }
        return filePath;
    }

    public static void main(String[] args) {
        System.out.println(StringUitl.getWebRootPath());
    }

    /**
     * 如果是null返回空字符串
     * @return arg
     */
    public static String SiftNull(String arg){
        String rt = "";
        if(arg != null && !"".equals(arg)){
            rt = arg;
        }
        return rt;
    }
    /**
     * 如果是null返回空字符串
     * @return arg
     */
    public static String SiftNull(Object arg){
        String rt = "";
        if(arg != null){
            rt = arg.toString();
        }
        return rt;
    }

    /**
     * 获取报表公共的map对象
     * @return
     */
    public static Map<String, Object> getReportHashMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        //Code条件限制
        map.put("condition_code", "code <> 'yw'");
        //mallCode条件限制
        map.put("condition_mallCode", "mall_code <> 'yw'");
        //buyMallCode条件限制
        map.put("condition_buyMallCode", "buy_mall_code <> 'yw'");
        //sellMallCode条件限制
        //map.put("condition_sellMallCode", "sell_mall_code NOT IN ('yw')");
        //portalorg_ou条件限制
        map.put("condition_portalorgOU", "portalorg_ou <> '00330040731'");
        //portalorg_ou条件限制
        map.put("condition_provinceOU", "province_orgou <> '00330040731'");
        //shopType条件限制
        map.put("condition_shopType", "shopType NOT IN (2,3,5,8)");
        //shopType条件限制
        map.put("condition_pid", "pid = -1");
        //下拉框条件限制yw
        map.put("yw", "'yw'");
        //查询条件中的group by条件限制
        map.put("condition_groupBy", "");
        return map;
    }

    /**
     * 获取组装List的共用map对象
     * @return
     */
    public static Map getCombineListMap(){
        Map map = new HashMap();
        map.put("mall_code", "zongji");
        map.put("mall_orgou", "zongji");
        map.put("orgName", "总计");
        map.put("amount", "0");
        map.put("sellNum", "0");
        map.put("saveamount", "0");
        map.put("prdUpNum", "0");
        map.put("OrderNum", "0");
        map.put("aver", "0");
        return map;
    }
    /**
     * 判断字符串是否为电话号码
     * String
     * @param arg
     * @return
     * @antuor zhangyanbing
     * Aug 22, 2014
     */
    public static boolean isTel(String arg){
        String matches= "(^[0-9]{11,12}$)";
        boolean result = false;
        if(arg != null && !"".equals(arg)){
            result = Pattern.matches(matches,arg);
        }
        return result;
    }


    /**
     * 判断字符串是否为条形码(判断是否为13位正整数）
     * String
     * @param arg
     * @return
     * @antuor shaozj
     * 2014-9-22 14:51:38
     */
    public static boolean isBarCode(String arg){
        String matches= "(^[0-9]{13}$)";
        boolean result = false;
        if(arg != null && !"".equals(arg)){
            result = Pattern.matches(matches,arg);
        }
        return result;
    }
    public static String arrayToString(String[] strArry,String exp){
        StringBuilder str = null;
        str = new StringBuilder();
        for (int i = 0; i < strArry.length; i++) {
            String s = strArry[i];
            str.append(s);
            if(i!=strArry.length-1){
                str.append(exp);
            }
        }
        return str.toString();
    }

    /**
     * 异常信息toString
     *
     * @param ex
     * @return
     */
    public static String getExceString(Exception ex){
        StackTraceElement [] arry_ex = ex.getStackTrace();
        String temp = ex.toString();
        for(int i=0;i<arry_ex.length;i++){
            temp += "\n"+arry_ex[i].toString();
        }
        System.out.println(temp);
        return temp;
    }
}
