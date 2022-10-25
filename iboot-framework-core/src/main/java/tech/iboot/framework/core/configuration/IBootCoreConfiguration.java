package tech.iboot.framework.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import tech.iboot.framework.core.bean.PackageInfo;
import tech.iboot.framework.core.properties.IBootProperties;
import tech.iboot.framework.core.utils.BannerPrinter;
import tech.iboot.framework.core.utils.ResourceBundleUtil;

import javax.annotation.Resource;

/**
 * Created on 2022/10/2
 * core模块启动配置类
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc 配置项有框架启动bean、聚合messageSource bean、关机处理等
 **/
public class IBootCoreConfiguration implements DisposableBean {
    private final Logger logger = LoggerFactory.getLogger(IBootCoreConfiguration.class);
    @Resource
    private IBootProperties iBootProperties;

    /**
     * Created on 2022/10/2
     * 框架启动bean
     * @author Hong Luo
     **/
    @Bean
    // @DependsOn(IBootBeanName.ASSEMBLE_MESSAGE_SOURCE)
    public void iBootInit() {
        BannerPrinter printer = new BannerPrinter(logger);
        if (iBootProperties.getBanner().isEnabled()) {
            printer.print(ResourceBundleUtil.getResourceByFileName("iBootBanner.txt"),
                    new PackageInfo(IBootCoreConfiguration.class));
        } else {
            printer.print(new PackageInfo(IBootCoreConfiguration.class));
        }
    }

    @Override
    public void destroy() throws Exception {
    }
}
