package tech.iboot.framework.sample.mybatisPlus.user.service;

import tech.iboot.framework.core.crud.CrudService;
import tech.iboot.framework.sample.mybatisPlus.user.entity.User;

import java.util.List;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public interface UserCrudService extends CrudService<User, String> {
    /**
     * 查询列表
     * @return List<User>
     */
    List<User> findListBySql();
}
