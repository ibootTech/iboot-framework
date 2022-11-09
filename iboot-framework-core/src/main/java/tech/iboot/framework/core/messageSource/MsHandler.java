package tech.iboot.framework.core.messageSource;

import tech.iboot.framework.core.configuration.IBootContextHolder;

/**
 * Created on 2022/10/5
 * 信息源工具
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc
 **/
public class MsHandler {
    private static MsHandler instance;
    private AssembleMessageSource assembleMessageSource;
    /**
     * Created on 2022/10/5
     * 实例化信息源工具
     * @author Hong Luo
     * @return 信息源工具
     **/
    public static MsHandler getInstance() {
        if(instance == null){
            instance = new MsHandler();
        }
        if (instance.assembleMessageSource == null) {
            instance.assembleMessageSource = IBootContextHolder.getBean(AssembleMessageSource.class);
        }
        return instance;
    }
    /**
     * Created on 2022/10/5
     * 根据code和信息源参数输出信息源
     * @author Hong Luo
     * @param code code
     * @param args 信息源参数
     * @return 信息源
     **/
    public String msg(String code, Object... args) {
        return msgWithDefault(code, code, args);
    }
    /**
     * Created on 2022/10/5
     * 根据code和信息源参数输出信息源，没有输出默认值
     * @author Hong Luo
     * @param code code
     * @param defaultMessage 默认值
     * @param args 信息源参数
     * @return 信息源
     **/
    public String msgWithDefault(String code, String defaultMessage, Object... args) {
        return this.assembleMessageSource.msgWithDefault(code, defaultMessage, args);
    }
}
