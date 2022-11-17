package tech.iboot.framework.core.crud;

import java.util.Collection;
import java.util.List;

/**
 * <strong>CRUD dao层接口</strong>
 * <p></p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public interface CrudDao<T, K> {
    /**
     * 根据主键id查询
     * @param id 主键id
     * @return T 实体
     */
    T findById(K id);

    /**
     * 根据主键id集合查询
     * @param ids 主键id集合
     * @return List<T> 实体列表
     */
    List<T> findByIds(Collection<K> ids);

    /**
     * 查询列表
     * @return List<T> 实体列表
     */
    List<T> findList();

    /**
     * 查询个数
     * @return Long 个数
     */
    Long findCount();

    /**
     * 插入
     * @param t 实体
     * @return T 实体
     */
    T insert(T t);

    /**
     * 批量插入
     * @param t 实体集合
     */
    void insertBatch(Collection<T> t);

    /**
     * 更新
     * @param t 实体
     * @return T 实体
     */
    T update(T t);

    /**
     * 根据主键id删除
     * @param id 主键id
     */
    void deleteById(K id);

    /**
     * 根据主键id集合批量删除
     * @param ids 主键id集合
     */
    void deleteBatchByIds(Collection<K> ids);
}
