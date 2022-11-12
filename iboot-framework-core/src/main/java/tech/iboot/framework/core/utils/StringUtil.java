package tech.iboot.framework.core.utils;

import java.util.function.Function;

/**
 * <strong>便捷字符串工具类</strong>
 * <p></p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public abstract class StringUtil {
    /**
     * 分隔符处理器，默认是逗号
     */
    private static final Function<String, String> DELIMITER_HANDLE = d -> d == null || "".equals(d) ? "," : d;

    /**
     * <strong>数组组转字符串，默认用逗号隔开</strong>
     * @param charSequences 数组
     * @return String 转换后字符串
     **/
    public static String arrayToString(Iterable<? extends CharSequence> charSequences) {
        return arrayToString(null, charSequences);
    }

    /**
     * <strong>数组组转字符串</strong>
     * @param delimiter 分隔符
     * @param charSequences 数组
     * @return String 转换后字符串
     **/
    public static String arrayToString(String delimiter, Iterable<? extends CharSequence> charSequences) {
        return charSequences == null ? "" : String.join(DELIMITER_HANDLE.apply(delimiter), charSequences);
    }

    /**
     * <strong>数组组转字符串，默认用逗号隔开</strong>
     * @param charSequence 数组
     * @return String 转换后字符串
     **/
    public static String arrayToString(CharSequence... charSequence) {
        return arrayToString(null, charSequence);
    }

    /**
     * <strong>数组组转字符串</strong>
     * @param delimiter 分隔符
     * @param charSequence 数组
     * @return String 转换后字符串
     **/
    public static String arrayToString(String delimiter, CharSequence... charSequence) {
        return charSequence == null ? "" : String.join(DELIMITER_HANDLE.apply(delimiter), charSequence);
    }
}
