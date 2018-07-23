package com.jhmk.earlywaring.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ziyu.zhou
 * @date 2018/7/20 10:47
 */

/**
 * 正则工具类
 */
public class RegularUtils {
    /**
     * ChineseCharacters cc 汉字
     * 删除汉字
     *
     * @param str
     * @return
     */
    public static String delCC(String str) {



        String reg = "[\u4e00-\u9fa5]";

        Pattern pat = Pattern.compile(reg);

        Matcher mat = pat.matcher(str);

        String repickStr = mat.replaceAll("");

        return repickStr;
    }
}

