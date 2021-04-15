package tech.iboot.commons.database.mybatis.core;

import tech.iboot.core.util.SpringContextUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author luohong
 * @date 2021/1/4
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
public class SqlBuilder {
    public String nativeSQL(Map params){
        return buildSql(params);
    }

    public String nativeSQLForCount(Map params){
        String sql = buildSql(params);
        sql = "select count(1) " + sql;
        return sql;
    }
    public String nativeSQLForOne(Map params){
        DataSource dataSource = SpringContextUtil.getInstance().getBean("druidDataSource");
        String dataSourceType = "";
        String sql = buildSql(params);
        try {
            dataSourceType = dataSource.getConnection().getMetaData().getDatabaseProductName();
            // todo 先实现mysql
            if ("MySQL".equals(dataSourceType)) {
                sql = sql +" LIMIT 1 ";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sql;
    }
    public String buildSql(Map params){
        String sql = params.get("sql").toString();
        if (params.get("params")!=null) {
            Object[] paramList = (Object[]) params.get("params");
            if (paramList!=null) {
                for (int i = 0; i < paramList.length; i++) {
                    String param =  paramList[i].toString();
                    sql = sql.replaceFirst("[?]","'"+param+"'");
                }
            }
        }
        return sql;
    }
}
