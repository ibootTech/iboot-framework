package tech.iboot.framework.starters.mybatisPlus.core;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import tech.iboot.framework.core.crud.CrudDao;

import java.util.Collection;
import java.util.List;

/**
 * <strong>CRUD dao层接口</strong>
 * <p></p>
 * Created on 2022/11/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@SuppressWarnings("rawtypes")
public interface BaseCrudDao<T, K extends java.io.Serializable> extends CrudDao<T, K> {

    /**
     * Mapper
     */
    BaseMapper<T> getMapper();

    /**
     * 根据主键id查询
     * @param id 主键id
     * @return 实体
     */
    default T findById(K id) {
       return getMapper().selectById(id);
    }

    /**
     * 根据主键id集合查询
     * @param ids 主键id集合
     * @return 实体列表
     */
    default List<T> findByIds(Collection<K> ids) {
        return getMapper().selectBatchIds(ids);
    }

    /**
     * 查询列表
     * @return 实体列表
     */
    default List<T> findList() {
        return getMapper().selectList(new LambdaQueryWrapper<>());
    }

    /**
     * 查询个数
     * @return 个数
     */
    default Long findCount() {
        return getMapper().selectCount(new LambdaQueryWrapper<>());
    }

    /**
     * 新增
     * @param t 实体
     * @return 实体
     */
    default T insert(T t) {
        getMapper().insert(t);
        return t;
    }

    /**
     * 批量新增
     * @param t 实体集合
     */
    default void insertBatch(Collection<T> t) {
        t.forEach(c -> getMapper().insert(c));
    }

    /**
     * 更新
     * @param t 实体
     * @return 实体
     */
    default T update(T t) {
        getMapper().updateById(t);
        return t;
    }

    /**
     * 根据主键id删除
     * @param id 主键id
     */
    default void deleteById(K id) {
        getMapper().deleteById(id);
    }

    /**
     * 根据主键id集合批量删除
     * @param ids 主键id集合
     */
    default void deleteBatchByIds(Collection<K> ids) {
        getMapper().deleteBatchIds(ids);
    };
}
