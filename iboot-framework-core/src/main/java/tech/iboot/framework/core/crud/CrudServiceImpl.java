package tech.iboot.framework.core.crud;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <strong>>CRUD service层实现类</strong>
 * <p></p>
 * Created on 2022/10/14
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class CrudServiceImpl<T, K, M extends CrudDao<T, K>> implements CrudService<T, K> {
    @Autowired
    M dao;

    /**
     * 重写getDao, 赋值dao
     * @return CrudDao<T, K> dao层
     */
    @Override
    public CrudDao<T, K> getDao() {
        return dao;
    }
}
