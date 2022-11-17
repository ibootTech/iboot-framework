package tech.iboot.framework.core.messageSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import tech.iboot.framework.core.utils.ResourceBundleUtil;
import tech.iboot.framework.core.utils.StringUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <strong>国际化信息源</strong>
 * <p>自动扫描获取资源文件夹i18n下文件，文件命名以_隔开</p>
 * Created on 2022/10/5
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class AbstractMessageSourceI18nConfiguration {

    /**
     * slf4j日志打印工具
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractMessageSourceI18nConfiguration.class);

    /**
     * i18n文件夹常量
     */
    private static final String I18N = "i18n";

    /**
     * 文件分隔符
     */
    private static final String SYMBOL = "_";

    /**
     * 获取资源文件路径函数
     */
    private static final Function<Resource, String> RESOURCE_PATH = r -> {
        try {
            return r.getURL().getPath();
        } catch (IOException ignored) {
            return null;
        }
    };

    /**
     * 获取资源文件路径数组函数
     */
    private static final Function<String, String[]> PATH_SUB = t -> {
        String p = t;
        p = p.substring(p.indexOf(I18N));
        p = p.substring(0, p.lastIndexOf("."));
        String[] splitPath = p.split("[/|\\\\]");
        String name = splitPath[splitPath.length - 1];
        name = name.contains(SYMBOL) ? name.substring(0, name.indexOf(SYMBOL)) : name;
        splitPath[splitPath.length - 1] = name;
        return splitPath;
    };
    /**
     * 获取i18n信息源
     * @return ResourceBundleMessageSource
     **/
    public static ResourceBundleMessageSource buildI18nMessageSourceConfiguration() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        try {
            Resource[] resource = ResourceBundleUtil.getResourcesByPathMatch(I18N + "/**");
            String msg = Arrays.stream(resource).map(RESOURCE_PATH)
                    .filter(p -> StringUtils.hasText(p) && (p.endsWith(ResourceBundleUtil.FILE_TYPE_PROPERTY) || p.endsWith(ResourceBundleUtil.FILE_TYPE_XML)))
                    .map(p -> {
                        String[] splitPath = PATH_SUB.apply(p);
                        messageSource.addBasenames(StringUtil.arrayToString("/", splitPath));
                        return "[MessageSource Resource Path]: "+p+",  [Name]: "+ splitPath[splitPath.length -1] + "\n";
                    }).collect(Collectors.joining());
            logger.info( "\n\n"+ AnsiOutput.toString(AnsiColor.BRIGHT_CYAN, AnsiStyle.ITALIC, msg, AnsiColor.DEFAULT, AnsiStyle.NORMAL));
        } catch (IOException ignored) {}
        return messageSource;
    }
}
