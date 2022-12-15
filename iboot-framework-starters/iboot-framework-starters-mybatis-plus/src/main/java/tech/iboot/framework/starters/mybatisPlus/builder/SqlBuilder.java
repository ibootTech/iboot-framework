package tech.iboot.framework.starters.mybatisPlus.builder;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.iboot.framework.core.configuration.SpringContextHolder;
import tech.iboot.framework.starters.mybatisPlus.core.DataBaseEnum;

import javax.sql.DataSource;
import java.util.Map;

/**
 * <strong>原生sql builder</strong>
 * <p>目前只支持mysql,以后将支持更多数据库</p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class SqlBuilder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 参数常量
     */
    public final static String PARAM = "params";

    /**
     * sql常量
     */
    public final static String SQL = "sql";

    /**
     * 生成原生sql
     * @param params 参数
     * @return String
     */
    public String nativeSql(Map<String, Object> params){
        String sql = "";
        if (DataBaseEnum.MySql.getValue().equals(getDatabaseId())) {
            sql =  buildSql(params);
        }
        return sql;
    }

    /**
     * 查询个数原生sql
     * @param params 参数
     * @return String
     */
    public String nativeSqlForCount(Map<String, Object> params){
        String sql = "";
        if (DataBaseEnum.MySql.getValue().equals(getDatabaseId())) {
            sql =  "select count(1) " + buildSql(params);
        }
        return sql;
    }

    /**
     * 查询原生sql
     * @param params 参数
     * @return String
     */
    public String nativeSqlForOne(Map<String, Object> params){
        String sql = "";
        if (DataBaseEnum.MySql.getValue().equals(getDatabaseId())) {
            sql =  buildSql(params) + " LIMIT 1 ";
        }
        return sql;
    }

    /**
     * 生成原生sql
     * @param params 参数
     * @return String
     */
    public String buildSql(Map<String, Object> params){
        String sql = params.get(SQL).toString();
        if (params.get(PARAM)!=null) {
            Object[] paramList = (Object[]) params.get(PARAM);
            if (paramList!=null) {
                for (Object o : paramList) {
                    String param = o.toString();
                    sql = sql.replaceFirst("[?]", "'" + param + "'");
                }
            }
        }
        return sql;
    }

    /**
     * 获取数据库类型
     * @return String
     */
    private String getDatabaseId() {
        DatabaseIdProvider bean = SpringContextHolder.getBean(DatabaseIdProvider.class);
        DataSource dataSource = SpringContextHolder.getBean(javax.sql.DataSource.class);
        String databaseId = "";
        try {
            databaseId =  bean.getDatabaseId(dataSource);
            logger.info("database id is " + databaseId);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return databaseId;
    }
}
