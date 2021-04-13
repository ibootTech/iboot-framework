package tech.iboot.commons.database.mybatis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author luohong
 * @date 2021/1/4
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Mapper {
    String value() default "";
}
