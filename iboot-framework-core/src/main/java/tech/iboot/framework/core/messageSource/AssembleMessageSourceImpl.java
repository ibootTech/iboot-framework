package tech.iboot.framework.core.messageSource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * <strong>聚合MessageSource实现类</strong>
 * <p></p>
 * Created on 2022/10/5
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class AssembleMessageSourceImpl implements AssembleMessageSource {

    /**
     * 信息源list，所有信息源都存在这
     */
    private final List<MessageSource> commonMessageSources = new CopyOnWriteArrayList<>();
    /**
     * 默认locale
     */
    private final Locale DEFAULT_LOCALE = Locale.getDefault();

    /**
     * code数组转字符串函数
     */
    private final Function<String[], String> getCodeByResolvable =
            c -> Optional.ofNullable(c).isPresent() ? c[c.length -1] : "";

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        for (MessageSource messageSource: commonMessageSources) {
            String result = messageSource.getMessage(code, args, null, locale);
            if (StringUtils.hasText(result)) {
                return result;
            }
        }
        return defaultMessage;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        for (MessageSource messageSource: commonMessageSources) {
            String result = messageSource.getMessage(code, args, locale);
            if (StringUtils.hasText(result)) {
                return result;
            }
        }
        throw new NoSuchMessageException(code, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        for (MessageSource messageSource: commonMessageSources) {
            String result = messageSource.getMessage(resolvable, locale);
            if (StringUtils.hasText(result)) {
                return result;
            }
        }
        throw new NoSuchMessageException(getCodeByResolvable.apply(resolvable.getCodes()), locale);
    }

    @Override
    public void addMessageSources(Collection<MessageSource> source) {
        commonMessageSources.addAll(source);
    }

    @Override
    public void addMessageSource(MessageSource source) {
        commonMessageSources.add(source);
    }

    @Override
    public String msg(String code, Object... args) {
        return msgWithDefault(code, code, args);
    }

    @Override
    public String msgWithDefault(String code, String defaultMessage, Object... args) {
        return msg(code, defaultMessage, DEFAULT_LOCALE, args);
    }

    @Override
    public String msg(String code, String defaultMessage, Locale locale, Object... args) {
        return getMessage(code, args, defaultMessage, locale);
    }
}
