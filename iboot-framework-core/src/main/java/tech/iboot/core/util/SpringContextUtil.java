package tech.iboot.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author luohong
 * @date 2020/10/6
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static SpringContextUtil util;

    static {
        util = new SpringContextUtil();
    }

    public static SpringContextUtil getInstance() {
        if (util == null) {
            util = new SpringContextUtil();
        }
        return util;
    }

    private ApplicationContext context;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.getInstance().context = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public ApplicationContext getContext() {
        return this.context;
    }

    /**
     * 获取对象
     *
     * @param name name
     * @return Object 一个以所给名字注册的bean的实例
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) throws BeansException {
        return (T) context.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     * @param clz clz
     * @return <T> T
     */
    public <T> T getBean(Class<T> clz) throws BeansException {
        @SuppressWarnings("unchecked")
        T result = (T) context.getBean(clz);
        return result;
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name name
     * @return boolean
     */
    public boolean containsBean(String name) {
        return context.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param name name
     * @return boolean
     */
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return context.isSingleton(name);
    }

    /**
     * @param name  name
     * @return Class 注册对象的类型
     */
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return context.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name name
     * @return String[]
     */
    public String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return context.getAliases(name);
    }
}
