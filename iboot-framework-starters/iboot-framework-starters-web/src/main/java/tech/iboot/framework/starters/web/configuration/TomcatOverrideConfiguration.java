package tech.iboot.framework.starters.web.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.core.env.Environment;

import java.io.File;

/**
 * <strong>tomcat重写配置类</strong>
 * <p>重写基础日志路径、访问日志等配置/p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@ConditionalOnClass(TomcatWebServerFactoryCustomizer.class)
public class TomcatOverrideConfiguration implements BeanPostProcessor {
    /**
     * 环境
     */
    @Autowired
    private Environment environment;

    /**
     * 服务配置
     */
    @Autowired
    private ServerProperties serverProperties;

    /**
     * web配置
     */
    @Autowired
    private WebProperties webProperties;

    /**
     * 服务初始化后重新配置TomcatWebServerFactoryCustomizer
     * @param bean bean对象
     * @param beanName bean名
     * @return TomcatWebServerFactoryCustomizer
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TomcatWebServerFactoryCustomizer) {
            if (webProperties.getTomcat().isOverride()) {
                serverProperties.getTomcat().getAccesslog().setEnabled(webProperties.getTomcat().getAccessLog().isEnabled());
                serverProperties.getTomcat().getAccesslog().setPattern(webProperties.getTomcat().getAccessLog().getPattern());
                serverProperties.getTomcat().setBasedir(new File(webProperties.getTomcat().getBasedir()));
                bean = new TomcatWebServerFactoryCustomizer(environment, serverProperties);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
