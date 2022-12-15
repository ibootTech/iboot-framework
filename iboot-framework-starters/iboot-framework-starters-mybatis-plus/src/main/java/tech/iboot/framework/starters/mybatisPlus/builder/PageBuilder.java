package tech.iboot.framework.starters.mybatisPlus.builder;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.iboot.framework.starters.mybatisPlus.core.PageModel;

import java.lang.reflect.Field;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class PageBuilder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String CURRENT_FIELD = "current";
    private final static String SIZE_FIELD = "size";
    public <K, T> IPage<T> build(K entity) {
        int current = -1;
        int size = -1;
        try {
            Class<?> pageClass = entity.getClass();
            if (!pageClass.isAssignableFrom(PageModel.class)) {
                pageClass = pageClass.getSuperclass();
            }
            Field currentField = pageClass.getDeclaredField(CURRENT_FIELD);
            Field sizeField = pageClass.getDeclaredField(SIZE_FIELD);
            currentField.setAccessible(true);
            sizeField.setAccessible(true);
            current  = (int) currentField.get(entity);
            size  = (int) sizeField.get(entity);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return new Page<>(current, size);
    }
}
