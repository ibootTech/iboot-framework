package com.sample.web.user.service;

import com.sample.web.user.dao.UserCrudDao;
import com.sample.web.user.model.User;
import org.springframework.stereotype.Service;
import tech.iboot.framework.core.crud.CrudServiceImpl;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/10/15
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@Service
public class UserServiceImpl extends CrudServiceImpl<User, String, UserCrudDao> implements UserCrudService {
}
