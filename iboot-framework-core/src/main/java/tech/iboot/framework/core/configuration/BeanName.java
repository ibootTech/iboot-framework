package tech.iboot.framework.core.configuration;

/**
 * <strong>bean名称常量</strong>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public abstract class BeanName {
    /**
     * 初始化bean名
     */
    @SuppressWarnings("SpellCheckingInspection")
    public final static String IBOOT_INIT = "iBootInit";
    /**
     * 聚合信息源bean名
     */
    public final static String ASSEMBLE_MESSAGE_SOURCE = "assembleMessageSource";
    /**
     * 跨域过滤bean名
     */
    public final static String CORS_FILTER = "corsFilter";
}
