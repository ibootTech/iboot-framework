package tech.iboot.framework.core.utils;

import java.util.function.Function;

/**
 * Created on 2022/10/5
 * 便捷字符串工具类
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc
 **/
public abstract class StringUtil {
    private static final Function<String, String> DELIMITER_HANDLE = d -> d == null || "".equals(d) ? "," : d;
    /**
     * Created on 2022/10/5
     * 数组组转字符串，默认用逗号隔开
     * @author Hong Luo
     * @param charSequences 数组
     * @return 转换后字符串
     **/
    public static String arrayToString(Iterable<? extends CharSequence> charSequences) {
        return arrayToString(null, charSequences);
    }

    /**
     * Created on 2022/10/5
     * 数组组转字符串
     * @author Hong Luo
     * @param delimiter 分隔符
     * @param charSequences 数组
     * @return 转换后字符串
     **/
    public static String arrayToString(String delimiter, Iterable<? extends CharSequence> charSequences) {
        return charSequences == null ? "" : String.join(DELIMITER_HANDLE.apply(delimiter), charSequences);
    }

    /**
     * Created on 2022/10/5
     * 数组组转字符串，默认用逗号隔开
     * @author Hong Luo
     * @param charSequence 数组
     * @return 转换后字符串
     **/
    public static String arrayToString(CharSequence... charSequence) {
        return arrayToString(null, charSequence);
    }

    /**
     * Created on 2022/10/5
     * 数组组转字符串
     * @author Hong Luo
     * @param delimiter 分隔符
     * @param charSequence 数组
     * @return 转换后字符串
     **/
    public static String arrayToString(String delimiter, CharSequence... charSequence) {
        return charSequence == null ? "" : String.join(DELIMITER_HANDLE.apply(delimiter), charSequence);
    }
}
