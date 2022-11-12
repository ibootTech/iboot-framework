package tech.iboot.framework.core.messageSource;

import org.springframework.context.MessageSource;

import java.util.Collection;
import java.util.Locale;

/**
 * <strong>聚合信息源接口</strong>
 * <p>聚合信息源输出</p>
 * Created on 2022/10/5
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public interface AssembleMessageSource extends MessageSource {
    /**
     * <strong>添加信息源</strong>
     * <p></p>
     * @param source 信息源集合
     **/
    void addMessageSources(Collection<MessageSource> source);

    /**
     * <strong>添加信息源</strong>
     * @param source 信息源
     **/
    void addMessageSource(MessageSource source);

    /**
     * <strong>根据code和信息源参数输出信息源</strong>
     * @param code code
     * @param args 信息源参数
     * @return String 信息源
     **/
    String msg(String code, Object... args);

    /**
     * <strong>根据code和信息源参数输出信息源，没有则输出默认值</strong>
     * @param code code
     * @param defaultMessage 默认值
     * @param args 信息源参数
     * @return String 信息源
     **/
    String msgWithDefault(String code, String defaultMessage, Object... args);

    /**
     * <strong>根据code、locale、信息源参数输出信息源，没有输出默认值</strong>
     * @param code code
     * @param defaultMessage 默认值
     * @param locale locale
     * @param args 信息源参数
     * @return String 信息源
     **/
    String msg(String code, String defaultMessage, Locale locale, Object... args);
}
