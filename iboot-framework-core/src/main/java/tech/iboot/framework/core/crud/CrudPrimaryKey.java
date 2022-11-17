package tech.iboot.framework.core.crud;

import java.lang.annotation.*;

/**
 * <strong>CRUD主键标识注解</strong>
 * <p></p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface CrudPrimaryKey {
}
