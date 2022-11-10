package tech.iboot.framework.core.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import java.util.Collection;

/**
 * Created on 2022/10/5
 * 上下文持有工具
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc 获取spring上下文、bean、推送事件等
 **/
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext;
    public SpringContextHolder() {}
    @Override
    public void destroy() throws Exception {
        clearHolder();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * Created on 2022/10/5
     * 获取上下文
     * @author Hong Luo
     * @return 上下文
     **/
    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    /**
     * Created on 2022/10/5
     * 根据类获取bean类型
     * @author Hong Luo
     * @param clazz 类
     * @return  bean类型
     **/
    public static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz).values();
    }

    /**
     * Created on 2022/10/5
     * 根据类获取bean
     * @author Hong Luo
     * @param clazz 类
     * @return  bean
     **/
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * Created on 2022/10/5
     * 根据bean名和类获取bean
     * @author Hong Luo
     * @param name bean名
     * @param clazz 类
     * @return  bean
     **/
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * Created on 2022/10/5
     * 断言是否存在上下文，没有则抛出异常
     * @author Hong Luo
     **/
    private static void assertApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext is null,please check that be injected in IBootContextHolder!");
        }
    }

    /**
     * Created on 2022/10/5
     * 推送事件
     * @author Hong Luo
     **/
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext != null) {
            try {
                applicationContext.publishEvent(event);
            } catch (Exception ignored) {}
        }
    }

    /**
     * Created on 2022/10/5
     * 清理本工具
     * @author Hong Luo
     **/
    public static void clearHolder() {
        applicationContext = null;
    }
}
