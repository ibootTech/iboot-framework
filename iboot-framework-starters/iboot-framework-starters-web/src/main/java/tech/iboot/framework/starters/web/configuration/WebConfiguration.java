package tech.iboot.framework.starters.web.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import tech.iboot.framework.core.configuration.BeanName;
import tech.iboot.framework.core.messageSource.AssembleMessageSource;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * <strong>web自动配置</strong>
 * <p>跨域bean配置</p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class WebConfiguration {
    /**
     * slf4j日志打印工具
     */
    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

    /**
     * 跨域过滤bean名
     */
    public final static String CORS_FILTER = "corsFilter";

    /**
     * web配置
     */
    @Resource
    private WebProperties webProperties;

    /**
     * 信息源
     */
    @Resource
    private AssembleMessageSource assembleMessageSource;

    /**
     * 跨域过滤bean
     * @return corsFilter
     */
    @Bean
    @DependsOn(BeanName.IBOOT_INIT)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        Optional.ofNullable(webProperties.getCorsFilters()).ifPresent(corsFilters -> {
            Consumer<WebProperties.CorsFilter> sd = t -> source.registerCorsConfiguration(t.getUrl(),
                    AbstractCorsConfiguration.buildCorsConfiguration(t));
            corsFilters.forEach(sd);
        });
        logger.info(AnsiOutput.toString(AnsiColor.GREEN,
                assembleMessageSource.msg("register.success", CORS_FILTER), AnsiColor.DEFAULT));
        return new CorsFilter(source);
    }
}
