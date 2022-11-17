package tech.iboot.framework.starters.web.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import tech.iboot.framework.core.properties.IBootProperties;

import java.util.List;

/**
 * <strong>web配置类</strong>
 * <p>配置跨域</p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@ConfigurationProperties(WebProperties.PREFIX )
public class WebProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = IBootProperties.PREFIX + ".web";
    /**
     * 跨域过滤配置
     */
    List<CorsFilter> corsFilters;

    /**
     * tomcat配置
     */
    Tomcat tomcat = new Tomcat();
    public static class CorsFilter {
        /**
         * 链接
         */
        private String url;
        /**
         * 域
         */
        private List<String> origins;
        /**
         * 头
         */
        private List<String> headers;
        /**
         * 方法
         */
        private List<String> methods;
        /**
         * 请求时长
         */
        private Long maxAge;
        /**
         * 是否允许证书
         */
        private Boolean allowCredentials;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getOrigins() {
            return origins;
        }

        public void setOrigins(List<String> origins) {
            this.origins = origins;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public void setHeaders(List<String> headers) {
            this.headers = headers;
        }

        public List<String> getMethods() {
            return methods;
        }

        public void setMethods(List<String> methods) {
            this.methods = methods;
        }

        public Long getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(Long maxAge) {
            this.maxAge = maxAge;
        }

        public Boolean getAllowCredentials() {
            return allowCredentials;
        }

        public void setAllowCredentials(Boolean allowCredentials) {
            this.allowCredentials = allowCredentials;
        }
    }
    public static class Tomcat {
        /**
         * 是否重写，重写则覆盖spring tomcat配置，默认值为true
         */
        private boolean override = true;
        /**
         * 访问日志
         */
        private AccessLog accessLog = new AccessLog();
        /**
         * 日志基础路径
         */
        private String basedir = "../";

        public boolean isOverride() {
            return override;
        }

        public void setOverride(boolean override) {
            this.override = override;
        }

        public AccessLog getAccessLog() {
            return accessLog;
        }

        public void setAccessLog(AccessLog accessLog) {
            this.accessLog = accessLog;
        }

        public String getBasedir() {
            return basedir;
        }

        public void setBasedir(String basedir) {
            this.basedir = basedir;
        }
    }

    public static class AccessLog {

        /**
         * 是否开启访问日志，默认值为true
         */
        private boolean enabled = true;

        /**
         * 日志输出格式
         */
        private String pattern = "%h %l %u %t \"%r\" %s %b %D %{User-Agent}i";

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }

    public List<CorsFilter> getCorsFilters() {
        return corsFilters;
    }

    public void setCorsFilters(List<CorsFilter> corsFilters) {
        this.corsFilters = corsFilters;
    }

    public Tomcat getTomcat() {
        return tomcat;
    }

    public void setTomcat(Tomcat tomcat) {
        this.tomcat = tomcat;
    }
}
