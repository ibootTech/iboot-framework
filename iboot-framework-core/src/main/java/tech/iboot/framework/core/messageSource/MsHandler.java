package tech.iboot.framework.core.messageSource;

import tech.iboot.framework.core.configuration.SpringContextHolder;

/**
 * <strong>信息源工具</strong>
 * <p>聚合信息源输出</p>
 * Created on 2022/10/5
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class MsHandler {

    /**
     * 信息源工具实例
     */
    private static MsHandler instance;

    /**
     * 聚合信息源
     */
    private AssembleMessageSource assembleMessageSource;

    /**
     * <strong>实例化信息源</strong>
     * @return MsHandler 信息源实例
     **/
    public static MsHandler getInstance() {
        if(instance == null){
            instance = new MsHandler();
        }
        if (instance.assembleMessageSource == null) {
            instance.assembleMessageSource = SpringContextHolder.getBean(AssembleMessageSource.class);
        }
        return instance;
    }

    /**
     * <strong>根据code和信息源参数输出信息源</strong>
     * @param code code
     * @param args 信息源参数
     * @return String 信息源
     **/
    public String msg(String code, Object... args) {
        return msgWithDefault(code, code, args);
    }

    /**
     * <strong>根据code和信息源参数输出信息源，没有则输出默认值</strong>
     * @param code code
     * @param defaultMessage 默认值
     * @param args 信息源参数
     * @return String 信息源
     **/
    public String msgWithDefault(String code, String defaultMessage, Object... args) {
        return this.assembleMessageSource.msgWithDefault(code, defaultMessage, args);
    }
}
