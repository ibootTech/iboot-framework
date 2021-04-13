package tech.iboot.commons.database.mybatis.core;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @author luohong
 * @date 2021/1/4
 * @remark
 * @email luohong@iboot.tech
 * @url https://iboot.tech
 **/
public interface IDefaultMapper<T> extends BaseMapper<T> {
    /**
     * 根据原生sql查询列表
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return List<Map>
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQL")
    List<Map> findListBySql(@Param("sql")String sql, @Param("params")Object... paramList);

    /**
     * 根据原生sql查询map
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return Map
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQLForOne")
    Map findBySql(@Param("sql")String sql, @Param("params")Object... paramList);

    @SelectProvider(type = SqlBuilder.class, method = "nativeSQLForOne")
    T findEntityBySql(@Param("sql")String sql,  @Param("params")Object... paramList);
    /**
     * 根据原生sql查询实体
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return T
     *//*
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQL")
    T findEntityBySql(@Param("sql")String sql, @Param("params")Object... paramList);*/

    /**
     * 根据原生sql查询实体列表
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return List<T>
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQL")
    List<T> findListEntityBySql(@Param("sql")String sql, @Param("params")Object... paramList);

    /**
     * 根据原生sql查询分页
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return List<Map>
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQL")
    IPage<Map> findListPageBySql(Page<Map> page, @Param("sql")String sql ,@Param("params")Object... paramList);

    /**
     * 根据原生sql查询分页
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return List<T>
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQL")
    IPage<T> findListPageEntityBySql(Page<T> page, @Param("sql")String sql,@Param("params")Object... paramList);

    /**
     * 根据原生sql查询分页
     *
     * @param sql sql语句
     * @param paramList 参数
     * @return List<T>
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSQLForCount")
    int findCountBySql(@Param("sql")String sql,@Param("params")Object... paramList);
}
