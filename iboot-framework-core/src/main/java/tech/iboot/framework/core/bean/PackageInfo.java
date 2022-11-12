package tech.iboot.framework.core.bean;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;
import java.util.function.BiFunction;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
/**
 * <strong>包信息</strong>
 * <p>版本号、标题、供应者</p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class PackageInfo {
    /**
     * 默认供应商
     */
    private static final String DEFAULT_VENDOR = "iboot.tech";
    /**
     * 默认标题
     */
    private static final String DEFAULT_TITLE = "iboot framework";
    /**
     * 默认版本号
     */
    private static final String DEFAULT_VERSION = "1.0.0";
    /**
     * 判断属性是否为空
     */
    private final BiFunction<String, Attributes.Name, Boolean> IS_NULL = (t1, t2) -> (t1 == null || "".equals(t1)) && t2!=null;
    /**
     * 版本号
     */
    private String version;
    /**
     * 标题
     */
    private String title;
    /**
     * 供应者
     */
    private String vendor;
    /**
     * 类
     */
    private Class<?> clazz;
    /**
     * jar包文件
     */
    private JarFile jarFile;
    private PackageInfo() {}
    /**
     * <strong>构造函数</strong>
     *
     * @param clazz 类
     **/
    public PackageInfo(Class<?> clazz) {
        this.clazz = clazz;
        this.version = getValueFromJar(this.clazz.getPackage().getImplementationVersion(), DEFAULT_VERSION, Attributes.Name.IMPLEMENTATION_VERSION);
        this.title = getValueFromJar(this.clazz.getPackage().getImplementationTitle(), DEFAULT_TITLE, Attributes.Name.IMPLEMENTATION_TITLE);
        this.vendor = getValueFromJar(this.clazz.getPackage().getImplementationVendor(), DEFAULT_VENDOR,
                Attributes.Name.IMPLEMENTATION_VENDOR, Attributes.Name.IMPLEMENTATION_VENDOR_ID);
        try {
            if (this.jarFile != null) {
                this.jarFile.close();
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * <strong>从jar中获取信息</strong>
     *
     * @param packageStr 字段信息
     * @param defaultStr 默认值
     * @param keys  字段标识
     * @return java.lang.String  字段信息
     **/
    private String getValueFromJar(String packageStr, String defaultStr, Attributes.Name ... keys){
        if (packageStr != null) {
            return packageStr;
        }
        try {
            this.jarFile = getJarFile();
            if (this.jarFile != null) {
                String result = this.jarFile.getManifest().getMainAttributes().getValue(keys[0]);
                if (IS_NULL.apply(result, keys[1])) {
                    result = this.jarFile.getManifest().getMainAttributes().getValue(keys[1]);
                    if (!IS_NULL.apply(result, keys[1])) {
                        return result;
                    }
                }
            }
        } catch (IOException ignored) {}
        return defaultStr;
    }

    /**
     * <strong>获取jar包信息</strong>
     *
     * @return java.util.jar.JarFile jar包文件
     **/
    private JarFile getJarFile() {
        if (this.jarFile != null) {
            return this.jarFile;
        }
        CodeSource codeSource = this.clazz.getProtectionDomain().getCodeSource();
        if (codeSource == null) {
            return null;
        }
        URL codeSourceLocation = codeSource.getLocation();
        try {
            URLConnection connection = codeSourceLocation.openConnection();
            if (connection instanceof JarURLConnection) {
                return ((JarURLConnection) connection).getJarFile();
            } else {
                return new JarFile(new File(codeSourceLocation.toURI()));
            }
        } catch (Exception ignored) {
            return null;
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
