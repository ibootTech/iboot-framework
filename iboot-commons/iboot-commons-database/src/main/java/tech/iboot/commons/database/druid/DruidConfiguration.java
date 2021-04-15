package tech.iboot.commons.database.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.iboot.commons.database.druid.DruidProperties;

/**
 * @author luohong
 * @date 2020/10/6
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
@Configuration
public class DruidConfiguration {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DruidProperties properties;

    @SuppressWarnings("unchecked")
    @Bean
    public ServletRegistrationBean statViewServlet() throws Exception {
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //设置ip白名单
        //servletRegistrationBean.addInitParameter("allow",allowIp);
        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        //servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        //设置控制台管理用户
        //ConfigTools.decrypt()
        servletRegistrationBean.addInitParameter("loginUsername",properties.getDruidConfigVo().getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", ConfigTools.decrypt(properties.getDruidConfigVo().getLoginPassword()));
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
}
