package tech.iboot.framework.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <strong>iBoot配置变量类</strong>
 * <p></p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@ConfigurationProperties(IBootProperties.PREFIX)
public class IBootProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "iboot";
    /**
     * 标语
     */
    Banner banner = new Banner();
    public static class Banner {
        /**
         * 是否开启，默认开启
         */
        private boolean enabled = true;
        public boolean isEnabled() {
            return enabled;
        }
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
