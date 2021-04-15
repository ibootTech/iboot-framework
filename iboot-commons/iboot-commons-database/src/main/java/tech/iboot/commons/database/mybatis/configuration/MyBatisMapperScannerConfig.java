package tech.iboot.commons.database.mybatis.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.iboot.commons.database.mybatis.annotation.Mapper;

/**
 * @author luohong
 * @date 2021/1/4
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
@Configuration
public class MyBatisMapperScannerConfig {
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("tech.iboot.**");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }
}
