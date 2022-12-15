package tech.iboot.framework.starters.mybatisPlus.configuration;

import org.jetbrains.annotations.NotNull;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Mapper 扫描配置</strong>
 * <p>注册bean定义、设置环境变量到配置文件</p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class MapperScannerConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    /**
     * 注册bean定义
     * @param importingClassMetadata 类元数据
     * @param registry bean注册
     * @param importBeanNameGenerator bean名创造器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, @NotNull BeanNameGenerator importBeanNameGenerator) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
        List<String> basePackages = new ArrayList<>(1);
        basePackages.add("tech.iboot.framework.starters.mybatisPlus.mapper");
        builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(basePackages));
        registry.registerBeanDefinition(importingClassMetadata.getClassName() + "#" + MapperScannerRegistrar.class.getSimpleName() + "#0", builder.getBeanDefinition());
        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry, importBeanNameGenerator);
    }

    /**
     * 设置环境变量到配置文件
     * @param environment 环境变量
     */
    @Override
    public void setEnvironment(@NotNull Environment environment) {
        new MybatisPlusProperties(environment);
    }
}
