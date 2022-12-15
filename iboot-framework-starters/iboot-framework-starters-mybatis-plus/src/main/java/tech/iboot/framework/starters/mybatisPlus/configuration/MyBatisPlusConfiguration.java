package tech.iboot.framework.starters.mybatisPlus.configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import tech.iboot.framework.core.bean.PackageInfo;
import tech.iboot.framework.core.configuration.BeanName;
import tech.iboot.framework.core.thread.IBootThreadLocalManager;
import tech.iboot.framework.core.utils.BannerPrinter;
import tech.iboot.framework.starters.mybatisPlus.annotation.Mapper;
import tech.iboot.framework.starters.mybatisPlus.core.DataBaseEnum;

import java.util.Objects;
import java.util.Properties;

/**
 * <strong>MyBatisPlus自动配置</strong>
 * <p>MybatisPlus过滤器bean、mapper扫描配置bean</p>
 * Created on 2022/11/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class MyBatisPlusConfiguration {

    /**
     * MybatisPlus过滤器bean
     * @return MybatisPlusInterceptor
     */
    @Bean
    @DependsOn(BeanName.IBOOT_INIT)
    public static MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(Long.parseLong(Objects.requireNonNull(
                IBootThreadLocalManager.getProperty(MybatisPlusProperties.PAGINATION_MAX_LIMIT, String.class))));
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        mybatisPlusInterceptor.addInnerInterceptor(new DataPermissionInterceptor());
        new BannerPrinter(MyBatisPlusConfiguration.class).print(new PackageInfo(MyBatisPlusConfiguration.class));
        return new MybatisPlusInterceptor();
    }

    /**
     * mapper扫描配置bean
     * @return MapperScannerConfigurer
     */
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        System.out.println("==" + IBootThreadLocalManager.getProperty(MybatisPlusProperties.SQL_SESSION_FACTORY_BEAN_NAME, String.class));
        System.out.println("==" + IBootThreadLocalManager.getProperty(MybatisPlusProperties.BASE_PACKAGE, String.class));
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(
                IBootThreadLocalManager.getProperty(MybatisPlusProperties.SQL_SESSION_FACTORY_BEAN_NAME, String.class));
        mapperScannerConfigurer.setBasePackage(IBootThreadLocalManager.getProperty(
                MybatisPlusProperties.BASE_PACKAGE, String.class));
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }

    /**
     * TODO 数据库提供者bean
     * <p>目前只支持mysql, 以后再添加</p>
     * @return DatabaseIdProvider
     */
    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty(DataBaseEnum.MySql.getName(), DataBaseEnum.MySql.getValue());
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }
}
