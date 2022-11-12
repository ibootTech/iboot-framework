package tech.iboot.framework.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <strong>类工具</strong>
 * <p></p>
 * Created on 2022/10/5
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class ClassUtil {

    /**
     * <strong>根据类获取所有字段</strong>
     * @param clazz 类
     * @return List<Field> 字段列表
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
     * <strong>根据类和注解类获取所有字段</strong>
     * @param clazz 类
     * @param annotationClass 注解类
     * @return List<Field> 字段列表
     **/
    public static <T, K extends Annotation> List<Field> getFieldByAnnotation(Class<T> clazz, Class<K> annotationClass) {
        List<Field> list = getField(clazz);
        return list == null || list.size() == 0 ? null: list.stream().filter(field -> field.getAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }

    /**
     * <strong>根据实体和注解类获取字段实际值</strong>
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
     * <strong>获取main函数所在类</strong>
     * @return Class<?> 主类
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
