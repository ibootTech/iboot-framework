package tech.iboot.framework.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import tech.iboot.framework.core.bean.PackageInfo;
import tech.iboot.framework.core.messageSource.AbstractMessageSourceI18nConfiguration;
import tech.iboot.framework.core.messageSource.AssembleMessageSource;
import tech.iboot.framework.core.messageSource.AssembleMessageSourceImpl;
import tech.iboot.framework.core.properties.IBootProperties;
import tech.iboot.framework.core.utils.BannerPrinter;
import tech.iboot.framework.core.utils.ResourceBundleUtil;

import java.util.Locale;
import java.util.stream.Collectors;

/**
 * <strong>core模块自动配置类</strong>
 * <p>配置项有框架启动bean、聚合messageSource bean、关机处理等</p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class CoreConfiguration implements DisposableBean {
    /**
     * slf4j日志打印工具
     */
    private final Logger logger = LoggerFactory.getLogger(CoreConfiguration.class);

    /**
     * 聚合信息源
     */
    private AssembleMessageSource assembleMessageSource;

    /**
     * IBoot配置
     */
    @Autowired
    private IBootProperties iBootProperties;

    /**
     * 框架启动bean
     **/
    @Bean
    @DependsOn(BeanName.ASSEMBLE_MESSAGE_SOURCE)
    public void iBootInit() {
        BannerPrinter printer = new BannerPrinter(logger);
        if (iBootProperties.getBanner().isEnabled()) {
            printer.print(ResourceBundleUtil.getResourceByFileName("iBootBanner.txt"),
                    new PackageInfo(CoreConfiguration.class));
        } else {
            printer.print(new PackageInfo(CoreConfiguration.class));
        }
    }

    /**
     * 聚合信息源bean
     *
     * @param objectProvider 信息源提供
     * @return MessageSource 信息源
     **/
    @Bean
    @Primary
    public MessageSource assembleMessageSource(ObjectProvider<MessageSource> objectProvider) {
        AssembleMessageSource assembleMessageSource = new AssembleMessageSourceImpl();
        assembleMessageSource.addMessageSource(AbstractMessageSourceI18nConfiguration.buildI18nMessageSourceConfiguration());
        assembleMessageSource.addMessageSources(objectProvider.stream().collect(Collectors.toList()));
        logger.info(AnsiOutput.toString(AnsiColor.GREEN,
                assembleMessageSource.getMessage("register.success", new String[]{BeanName.ASSEMBLE_MESSAGE_SOURCE},
                        Locale.getDefault()), AnsiColor.DEFAULT));
        this.assembleMessageSource = assembleMessageSource;
        return  assembleMessageSource;
    }

    /**
     * 销毁程序
     **/
    @Override
    public void destroy() throws Exception {
        logger.info(AnsiOutput.toString(AnsiColor.RED,
                assembleMessageSource.msg("destroy.shutDownHook"), AnsiColor.DEFAULT));
    }
}
