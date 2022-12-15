package tech.iboot.framework.sample.mybatisPlus.user.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tech.iboot.framework.sample.mybatisPlus.user.entity.User;
import tech.iboot.framework.sample.mybatisPlus.user.mapper.UserMapper;
import tech.iboot.framework.sample.mybatisPlus.user.service.UserCrudService;
import tech.iboot.framework.starters.mybatisPlus.core.BaseCrudServiceImpl;

import java.util.List;


/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@Service
public class UserCrudServiceImpl extends BaseCrudServiceImpl<User, String, UserMapper> implements UserCrudService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> findListBySql() {
        return userMapper.findListBySql("select * from test_user");
    }
}
