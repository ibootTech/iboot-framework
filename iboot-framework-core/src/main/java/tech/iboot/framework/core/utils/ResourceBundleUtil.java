package tech.iboot.framework.core.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created on 2022/10/2
 * 资源文件读取工具
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc
 **/
public abstract class ResourceBundleUtil {
    public static final String FILE_TYPE_PROPERTY = ".properties";
    public static final String FILE_TYPE_XML = ".xml";
    public static Resource getResourceByFileName(String fileName) {
        return new DefaultResourceLoader((ClassLoader)null).getResource(fileName);
    }
    public static Resource getResourceByFileName(ClassLoader classLoader, String fileName) {
        return new DefaultResourceLoader(classLoader).getResource(fileName);
    }
    public static Resource[] getResourcesByPathMatch(String pathMatch) throws IOException {
        return new PathMatchingResourcePatternResolver(new DefaultResourceLoader((ClassLoader)null)).getResources("classpath*:" + pathMatch);
    }
    public ResourceBundle getResourceBundleByFileName(String fileName) {
        return ResourceBundle.getBundle(fileName);
    }
}
