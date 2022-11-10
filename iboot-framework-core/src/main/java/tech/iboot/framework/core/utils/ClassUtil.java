package tech.iboot.framework.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2022/10/6
 * 类工具
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc 类工具
 **/
public class ClassUtil {

    /**
     * Created on 2022/10/6
     * 根据类获取所有字段
     * @author Hong Luo
     * @param clazz 类
     * @return List<Field>
     **/
    public static <T> List<Field> getField(Class<T> clazz) {
        List<Field> list = new ArrayList<>();
        if (clazz.getSuperclass() !=null) {
            list.addAll(getField(clazz.getSuperclass()));
        }
        list.addAll(Arrays.stream(clazz.getFields()).collect(Collectors.toList()));
        list.addAll(Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Created on 2022/10/6
     * 根据类和注解类获取所有字段
     * @author Hong Luo
     * @param clazz 类
     * @param annotationClass 注解类
     * @return List<Field>
     **/
    public static <T, K extends Annotation> List<Field> getFieldByAnnotation(Class<T> clazz, Class<K> annotationClass) {
        List<Field> list = getField(clazz);
        return list == null || list.size() == 0 ? null: list.stream().filter(field -> field.getAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }

    /**
     * Created on 2022/11/10
     * 根据实体和注解类获取字段实际值
     * @author Hong Luo
     * @param t 实体
     * @param annotationClass 注解类
     * @return V 字段实际值
     **/
    @SuppressWarnings("unchecked")
    public static <T, K extends Annotation, V> V getVariableByFirstField(T t, Class<K> annotationClass) {
        List<Field> list = getFieldByAnnotation(t.getClass(), annotationClass);
        if (list!=null && list.size() > 0) {
            try {
                Field field = list.get(0);
                field.setAccessible(true);
                return (V) field.get(t);
            } catch (Exception ignored) {
                return null;
            }
        }
        return null;
    }

    /**
     * Created on 2022/10/6
     * 获取main函数类
     * @author Hong Luo
     * @return 主类
     **/
    public static Class<?> deduceMainApplicationClass() {
        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        } catch (ClassNotFoundException ignored) {
        }
        return null;
    }
}
