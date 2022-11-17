package tech.iboot.framework.core.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

import java.util.Collection;

/**
 * <strong>spring上下文持有工具</strong>
 * <p>获取spring上下文、bean、推送事件等</p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    /**
     * Spring Application 上下文
     */
    private static ApplicationContext applicationContext;
    public SpringContextHolder() {}

    /**
     * 销毁后清理Holder
     * @throws Exception 异常
     **/
    @Override
    public void destroy() throws Exception {
        clearHolder();
    }

    /**
     * 设置Holder
     *
     * @param applicationContext spring Application 上下文
     **/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 获取上下文
     *
     * @return 上下文
     **/
    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    /**
     * 根据类获取bean类型
     *
     * @param clazz 类
     * @return java.util.Collection<T>  bean类型
     **/
    public static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz).values();
    }

    /**
     * 根据类获取bean
     *
     * @param clazz 类
     * @return bean
     **/
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据bean名和类获取bean
     *
     * @param name bean名
     * @param clazz 类
     * @return bean
     **/
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 断言是否存在上下文，没有则抛出异常
     **/
    private static void assertApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext is null,please check that be injected in IBootContextHolder!");
        }
    }

    /**
     * 推送事件
     *
     * @param event 事件
     **/
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext != null) {
            try {
                applicationContext.publishEvent(event);
            } catch (Exception ignored) {}
        }
    }

    /**
     * 清理Holder
     **/
    public static void clearHolder() {
        applicationContext = null;
    }
}
