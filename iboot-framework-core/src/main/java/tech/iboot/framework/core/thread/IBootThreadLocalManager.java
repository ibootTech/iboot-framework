package tech.iboot.framework.core.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <strong>全局ThreadLocal管理</strong>
 * <p>
 *  THREAD_LOCAL_PROPERTY为了解决bean加载顺序获取不到配置，所以把配置信息写到ThreadLocal
 *  考虑线程安全不用InheritableThreadLocal，继续使用ThreadLocal，缺点是只有主线程能获取</p>
 *  子线程再从配置文件或配置类获取吧
 * </p>
 * Created on 2022/10/6
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public final class IBootThreadLocalManager {
    /**
     * 配置信息ThreadLocal
     */
    private static final ThreadLocal<ConcurrentHashMap<String, Object>>
            THREAD_LOCAL_PROPERTY = new ThreadLocal<>();


    /**
     * <strong>配置信息保存到ThreadLocal</strong>
     * @param key key
     * @param object 值
     **/
    public static <T> void setProperty(String key, T object) {
        getProperties().put(key, object);
    }

    /**
     * <strong>从ThreadLocal获取配置</strong>
     * @param key key
     * @param clazz 类
     * @return T 配置实体
     **/
    public static <T> T getProperty(String key, Class<T> clazz) {
        return getProperties().get(key) == null
                ? null : clazz.cast(getProperties().get(key));
    }

    /**
     * <strong>从ThreadLocal获取配置</strong>
     * @return ConcurrentHashMap 配置
     **/
    public static ConcurrentHashMap<String, Object> getProperties() {
        if(THREAD_LOCAL_PROPERTY.get() == null) {
            THREAD_LOCAL_PROPERTY.set(new ConcurrentHashMap<>(128));
        }
        return THREAD_LOCAL_PROPERTY.get();
    }

    /**
     * <strong>清理ThreadLocal</strong>
     **/
    public static void clean() {
        THREAD_LOCAL_PROPERTY.remove();
    }
}
