package tech.iboot.framework.core.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * <strong>资源文件读取工具</strong>
 * <p></p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public abstract class ResourceBundleUtil {
    /**
     * properties文件后缀名常量
     */
    public static final String FILE_TYPE_PROPERTY = ".properties";

    /**
     * xml文件后缀名常量
     */
    public static final String FILE_TYPE_XML = ".xml";

    /**
     * <strong>根据文件名获取资源文件</strong>
     * @param fileName 文件名
     * @return Resource 资源文件
     **/
    public static Resource getResourceByFileName(String fileName) {
        return new DefaultResourceLoader((ClassLoader)null).getResource(fileName);
    }

    /**
     * <strong>根据类加载器和文件名获取资源文件</strong>
     * @param classLoader 类加载器
     * @param fileName 文件名
     * @return Resource 资源文件
     **/
    public static Resource getResourceByFileName(ClassLoader classLoader, String fileName) {
        return new DefaultResourceLoader(classLoader).getResource(fileName);
    }

    /**
     * <strong>根据路径匹配信息获取资源文件数组</strong>
     * @param pathMatch 路径匹配信息
     * @return Resource[] 资源文件数组
     **/
    public static Resource[] getResourcesByPathMatch(String pathMatch) throws IOException {
        return new PathMatchingResourcePatternResolver(new DefaultResourceLoader((ClassLoader)null)).getResources("classpath*:" + pathMatch);
    }
    public ResourceBundle getResourceBundleByFileName(String fileName) {
        return ResourceBundle.getBundle(fileName);
    }
}
