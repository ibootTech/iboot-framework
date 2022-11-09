package tech.iboot.framework.core.messageSource;

import org.springframework.context.MessageSource;

import java.util.Collection;
import java.util.Locale;

/**
 * Created on 2022/10/5
 * 聚合信息源接口
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc 获取i18n文件夹下资源文件，信息源输出
 **/
public interface AssembleMessageSource extends MessageSource {
    /**
     * Created on 2022/10/5
     * 添加信息源
     * @author Hong Luo
     * @param source 信息源集合
     **/
    void addMessageSources(Collection<MessageSource> source);

    /**
     * Created on 2022/10/5
     * 添加信息源
     * @author Hong Luo
     * @param source 信息源
     **/
    void addMessageSource(MessageSource source);

    /**
     * Created on 2022/10/5
     * 根据code和信息源参数输出信息源
     * @author Hong Luo
     * @param code code
     * @param args 信息源参数
     * @return 信息源
     **/
    String msg(String code, Object... args);

    /**
     * Created on 2022/10/5
     * 根据code和信息源参数输出信息源，没有输出默认值
     * @author Hong Luo
     * @param code code
     * @param defaultMessage 默认值
     * @param args 信息源参数
     * @return 信息源
     **/
    String msgWithDefault(String code, String defaultMessage, Object... args);

    /**
     * Created on 2022/10/5
     * 根据code、locale、信息源参数输出信息源，没有输出默认值
     * @author Hong Luo
     * @param code code
     * @param locale locale
     * @param defaultMessage 默认值
     * @param args 信息源参数
     * @return 信息源
     **/
    String msg(String code, String defaultMessage, Locale locale, Object... args);
}
