package tech.iboot.framework.starter.web.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import tech.iboot.framework.core.utils.StringUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * <strong>抽象跨域配置类</strong>
 * <p>获取跨域配置信息</p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public abstract class AbstractCorsConfiguration {
    /**
     * slf4j日志打印工具
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractCorsConfiguration.class);

    /**
     * 设置域函数
     */
    private static final BiConsumer<List<String>, CorsConfiguration> SET_ORIGINS = (t, r) -> {
        if (t != null && t.size() > 0) {
            r.setAllowedOrigins(t);
        } else {
            r.addAllowedOrigin(CorsConfiguration.ALL);
        }
    };

    /**
     * 设置头函数
     */
    private static final BiConsumer<List<String>,CorsConfiguration> SET_HEADERS = (t, r) -> {
        if (t != null && t.size() > 0) {
            r.setAllowedHeaders(t);
        } else {
            r.addAllowedHeader(CorsConfiguration.ALL);
        }
    };

    /**
     * 设置方法函数
     */
    private static final BiConsumer<List<String>, CorsConfiguration> SET_METHODS = (t, r) -> {
        if (t != null && t.size() > 0) {
            t.forEach(m -> {
                if (CorsConfiguration.ALL.equals(m)) {
                    r.addAllowedMethod(m);
                } else {
                    Optional.ofNullable(HttpMethod.resolve(m)).ifPresent(r::addAllowedMethod);
                }
            });
        } else {
            r.addAllowedMethod(CorsConfiguration.ALL);
        }
    };

    /**
     * <strong>跨域配置</strong>
     * <p></p>
     * @param corsFilter 跨域配置
     * @return CorsConfiguration 跨域配置
     **/
    public static CorsConfiguration buildCorsConfiguration(WebProperties.CorsFilter corsFilter) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        SET_ORIGINS.accept(corsFilter.getOrigins(), corsConfiguration);
        SET_HEADERS.accept(corsFilter.getHeaders(), corsConfiguration);
        SET_METHODS.accept(corsFilter.getMethods(), corsConfiguration);
        Optional.ofNullable(corsFilter.getMaxAge()).ifPresent(corsConfiguration::setMaxAge);
        Optional.ofNullable(corsFilter.getAllowCredentials()).ifPresent(corsConfiguration::setAllowCredentials);
        logger.info(AnsiOutput.toString(AnsiColor.BRIGHT_CYAN, AnsiStyle.ITALIC,
                        "CorsFilter Config Url => {} , Origins => {}, Headers => {} , Methods => {} , MaxAge => {}, AllowCredentials => {}",
                        AnsiColor.DEFAULT, AnsiStyle.NORMAL), corsFilter.getUrl(),
                StringUtil.arrayToString(corsConfiguration.getAllowedOrigins()),
                StringUtil.arrayToString(corsConfiguration.getAllowedHeaders()),
                StringUtil.arrayToString(corsConfiguration.getAllowedMethods()), corsConfiguration.getMaxAge(), corsConfiguration.getAllowCredentials());
        return corsConfiguration;
    }
}
