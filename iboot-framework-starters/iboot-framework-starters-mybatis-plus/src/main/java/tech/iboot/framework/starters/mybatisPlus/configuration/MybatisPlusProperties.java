package tech.iboot.framework.starters.mybatisPlus.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import tech.iboot.framework.core.thread.IBootThreadLocalManager;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * <strong>MybatisPlus配置类</strong>
 * <p></p>
 * Created on 2022/11/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@ConfigurationProperties(MybatisPlusProperties.PREFIX)
public class MybatisPlusProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "mybatis-plus.iboot";

    /**
     * mapper 配置名
     */
    public static final String MAPPER = PREFIX + ".mapper";

    /**
     * sql session bean 配置名
     */
    public static final String SQL_SESSION_FACTORY_BEAN_NAME = MAPPER + ".sql-session-factory-bean-name";

    /**
     * 包 配置名
     */
    public static final String BASE_PACKAGE = MAPPER + ".base-package";

    /**
     * 分页 配置名
     */
    public static final String PAGINATION = PREFIX + ".pagination";

    /**
     * 分页查询最大总数限制 配置名
     */
    public static final String PAGINATION_MAX_LIMIT = PAGINATION + ".max-limit";

    /**
     * MybatisPlus配置构造函数
     * @param environment 环境
     */
    public MybatisPlusProperties(Environment environment) {
        BiConsumer<String, Object> setProperty = (k, v) -> {
            if (IBootThreadLocalManager.getProperty(k, v.getClass()) == null) {
                IBootThreadLocalManager.setProperty(k, environment.getProperty(k) == null ?
                        v : Objects.requireNonNull(environment.getProperty(k)));
            }
        };
        setProperty.accept(SQL_SESSION_FACTORY_BEAN_NAME, mapper.getSqlSessionFactoryBeanName());
        setProperty.accept(BASE_PACKAGE, mapper.getBasePackage());
        setProperty.accept(PAGINATION_MAX_LIMIT, pagination.getMaxLimit() + "");
    }

    public MybatisPlusProperties() {
    }

    /**
     * mapper配置
     */
    Mapper mapper = new Mapper();

    /**
     * 分页配置
     */
    Pagination pagination = new Pagination();

    public static class Pagination {
        /**
         * 分页查询最大总数限制
         */
        private Long maxLimit = 1000L;
        public Long getMaxLimit() {
            return maxLimit;
        }
        public void setMaxLimit(Long maxLimit) {
            this.maxLimit = maxLimit;
        }
    }

    public static class Mapper {
        /**
         * sql session bean
         */
        private String sqlSessionFactoryBeanName = "sqlSessionFactory";

        /**
         * 包
         */
        private String basePackage = "tech.iboot";

        public String getSqlSessionFactoryBeanName() {
            return sqlSessionFactoryBeanName;
        }

        public void setSqlSessionFactoryBeanName(String sqlSessionFactoryBeanName) {
            this.sqlSessionFactoryBeanName = sqlSessionFactoryBeanName;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
