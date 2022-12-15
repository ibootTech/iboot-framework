package tech.iboot.framework.starters.mybatisPlus.core;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tech.iboot.framework.core.crud.CrudDao;
import tech.iboot.framework.core.crud.CrudService;

import java.util.Collection;
import java.util.List;

/**
 * <strong>CRUD service层实现</strong>
 * <p></p>
 * Created on 2022/11/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class BaseCrudServiceImpl<T, K extends java.io.Serializable, M extends BaseMapper<T>>
        implements CrudService<T, K> {
    @Autowired
    M baseCrudMapper;
    @Override
    public CrudDao<T, K> getDao() {
        return null;
    }

    @Override
    public T findById(K id) {
        return baseCrudMapper.selectById(id);
    }

    @Override
    public List<T> findByIds(Collection<K> ids) {
        return baseCrudMapper.selectBatchIds(ids);
    }

    @Override
    public List<T> findList() {
        return baseCrudMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public Long findCount() {
        return baseCrudMapper.selectCount(new LambdaQueryWrapper<>());
    }

    @Override
    public T insert(T t) {
        baseCrudMapper.insert(t);
        return t;
    }

    @Override
    public void insertBatch(Collection<T> t) {
        t.forEach(c -> baseCrudMapper.insert(c));
    }

    @Override
    public T update(T t) {
        baseCrudMapper.updateById(t);
        return t;
    }

    @Override
    public void deleteById(K id) {
        baseCrudMapper.deleteById(id);
    }

    @Override
    public void deleteBatchByIds(Collection<K> ids) {
        baseCrudMapper.deleteBatchIds(ids);
    }
}
