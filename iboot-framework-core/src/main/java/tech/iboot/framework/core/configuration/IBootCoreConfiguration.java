package tech.iboot.framework.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectProvider;
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

import javax.annotation.Resource;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created on 2022/10/2
 * core模块启动配置类
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc 配置项有框架启动bean、聚合messageSource bean、关机处理等
 **/
public class IBootCoreConfiguration implements DisposableBean {
    private final Logger logger = LoggerFactory.getLogger(IBootCoreConfiguration.class);

    private AssembleMessageSource assembleMessageSource;
    @Resource
    private IBootProperties iBootProperties;

    /**
     * Created on 2022/10/2
     * 框架启动bean
     * @author Hong Luo
     **/
    @Bean
    @DependsOn(IBootBeanName.ASSEMBLE_MESSAGE_SOURCE)
    public void iBootInit() {
        BannerPrinter printer = new BannerPrinter(logger);
        if (iBootProperties.getBanner().isEnabled()) {
            printer.print(ResourceBundleUtil.getResourceByFileName("iBootBanner.txt"),
                    new PackageInfo(IBootCoreConfiguration.class));
        } else {
            printer.print(new PackageInfo(IBootCoreConfiguration.class));
        }
    }

    /**
     * Created on 2022/10/5
     * 聚合信息源bean
     * @author Hong Luo
     **/
    @Bean
    @Primary
    public MessageSource assembleMessageSource(ObjectProvider<MessageSource> objectProvider) {
        AssembleMessageSource assembleMessageSource = new AssembleMessageSourceImpl();
        assembleMessageSource.addMessageSource(AbstractMessageSourceI18nConfiguration.buildCorsConfiguration());
        assembleMessageSource.addMessageSources(objectProvider.stream().collect(Collectors.toList()));
        logger.info(AnsiOutput.toString(AnsiColor.GREEN,
                assembleMessageSource.getMessage("register.success", new String[]{IBootBeanName.ASSEMBLE_MESSAGE_SOURCE},
                        Locale.getDefault()), AnsiColor.DEFAULT));
        this.assembleMessageSource = assembleMessageSource;
        return  assembleMessageSource;
    }

    /**
     * Created on 2022/10/2
     * 销毁程序
     * @author Hong Luo
     **/
    @Override
    public void destroy() throws Exception {
        logger.info(AnsiOutput.toString(AnsiColor.RED,
                assembleMessageSource.msg("destroy.shutDownHook"), AnsiColor.DEFAULT));
    }
}
