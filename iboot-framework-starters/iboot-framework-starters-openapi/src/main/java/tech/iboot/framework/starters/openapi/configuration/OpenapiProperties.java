package tech.iboot.framework.starters.openapi.configuration;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <strong>openapi 配置项</strong>
 * <p></p>
 * Created on 2022/10/15
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@ConfigurationProperties(OpenapiProperties.PREFIX)
public class OpenapiProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "iboot.openapi";

    /**
     * 构造函数
     */
    public OpenapiProperties() {}

    /**
     * swagger文档信息
     */
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
