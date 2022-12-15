package tech.iboot.framework.starters.mybatisPlus.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <strong>自定义mapper注解</strong>
 * <p></p>
 * Created on 2022/11/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Mapper {
}
