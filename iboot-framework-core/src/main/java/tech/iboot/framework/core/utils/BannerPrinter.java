package tech.iboot.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiBackground;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.io.Resource;
import org.springframework.core.log.LogMessage;
import org.springframework.util.StreamUtils;
import tech.iboot.framework.core.bean.PackageInfo;

import java.nio.charset.StandardCharsets;

/**
 * <strong>标语打印工具</strong>
 * <p></p>
 * Created on 2022/10/2
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class BannerPrinter {

    /**
     * slf4j日志打印工具
     */
    private Logger logger = LoggerFactory.getLogger(BannerPrinter.class);

    public BannerPrinter() {}

    /**
     * <strong>构造函数</strong>
     * @param classMain   需要打印的类
     **/
    public BannerPrinter(Class<?> classMain) {
        logger = LoggerFactory.getLogger(classMain);
    }

    /**
     * <strong>构造函数</strong>
     * @param logger slf4j打印对象
     **/
    public BannerPrinter(Logger logger) {
        this.logger = logger;
    }

    /**
     * <strong>打印资源文件信息</strong>
     * @param resource 资源文件
     **/
    public void print(Resource resource) {
        logger.info("\n\n" + resourcePrint(resource) + "\n");
    }

    /**
     * <strong>打印资源文件信息和包信息</strong>
     * @param resource 资源文件
     * @param info 包信息
     **/
    public void print(Resource resource, PackageInfo info) {
        logger.info("\n\n" + resourcePrint(resource) + "\n" + packageInfoPrint(info) + "\n");
    }

    /**
     * <strong>打印包信息</strong>
     * @param info 包信息
     **/
    public void print(PackageInfo info) {
        logger.info("\n\n" + packageInfoPrint(info) + "\n");
    }

    /**
     * <strong>输出资源文件信息</strong>
     * @param resource 资源文件
     * @return String 资源字符串
     **/
    private String resourcePrint(Resource resource) {
        try {
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.warn(String.valueOf(LogMessage.format("Banner not printable: %s (%s: '%s')", resource, e.getClass(), e.getMessage())), e);
        }
        return "";
    }

    /**
     * <strong>输出包信息</strong>
     * @param info 包信息
     * @return String 包信息字符串
     **/
    private String packageInfoPrint(PackageInfo info) {
        return AnsiOutput.toString(
                AnsiStyle.ITALIC, AnsiStyle.BOLD,
                AnsiBackground.GREEN, AnsiColor.BLACK, " package ", AnsiBackground.DEFAULT, AnsiColor.GREEN, " -> ",info.getTitle(), "    ",
                AnsiBackground.CYAN, AnsiColor.BLACK, " version ", AnsiBackground.DEFAULT, AnsiColor.CYAN, " -> ",info.getVersion(), "    ",
                AnsiBackground.MAGENTA, AnsiColor.BLACK, " vendor ", AnsiBackground.DEFAULT, AnsiColor.MAGENTA, " -> ",info.getVendor(), "    ",
                AnsiBackground.DEFAULT, AnsiColor.DEFAULT, AnsiStyle.NORMAL);
    }
}
