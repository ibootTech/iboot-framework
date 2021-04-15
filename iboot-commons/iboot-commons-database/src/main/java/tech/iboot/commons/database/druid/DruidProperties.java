package tech.iboot.commons.database.druid;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author luohong
 * @date 2018/04/6
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
@ConfigurationProperties(prefix = "tech.iboot.database.druid")
@Component
public class DruidProperties {
    private DruidConfigVo config = new DruidConfigVo();
    public DruidConfigVo getDruidConfigVo() {
        return config;
    }
    public void setConfig(DruidConfigVo config) {
        this.config = config;
    }
}
