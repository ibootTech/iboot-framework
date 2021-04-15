package tech.iboot.commons.database.mybatis.core;

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
        String sql = buildSql(params);
        sql = sql +" LIMIT 1 ";
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
