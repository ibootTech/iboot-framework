package tech.iboot.framework.core.crud;

import java.util.Collection;
import java.util.List;

/**
 * <strong>>CRUD service层接口</strong>
 * <p></p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public interface CrudService<T, K> {
    /**
     * dao层
     * @return CrudDao<T, K> dao层
     */
    CrudDao<T, K> getDao();

    /**
     * 根据主键id查询
     * @param id 主键id
     * @return T 实体
     */
    default T findById(K id) {
        return getDao().findById(id);
    }

    /**
     * 根据主键id集合查询
     * @param ids 主键id集合
     * @return List<T> 实体列表
     */
    default List<T> findByIds(Collection<K> ids) {
        return getDao().findByIds(ids);
    }

    /**
     * 查询列表
     * @return List<T> 实体列表
     */
    default List<T> findList() {
        return getDao().findList();
    }

    /**
     * 查询个数
     * @return Long 个数
     */
    default Long findCount() {
        return getDao().findCount();
    }

    /**
     * 插入
     * @param t 实体
     * @return T 实体
     */
    default T insert(T t) {
        return getDao().insert(t);
    }

    /**
     * 批量插入
     * @param t 实体集合
     */
    default void insertBatch(Collection<T> t) {
        getDao().insertBatch(t);
    }

    /**
     * 更新
     * @param t 实体
     * @return T 实体
     */
    default T update(T t) {
        return getDao().update(t);
    }

    /**
     * 根据主键id删除
     * @param id 主键id
     */
    default void deleteById(K id) {
        getDao().deleteById(id);
    }

    /**
     * 根据主键id集合批量删除
     * @param ids 主键id集合
     */
    default void deleteBatchByIds(Collection<K> ids) {
        getDao().deleteBatchByIds(ids);
    }
}
