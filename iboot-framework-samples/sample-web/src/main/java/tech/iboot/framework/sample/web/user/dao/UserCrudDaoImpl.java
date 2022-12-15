package tech.iboot.framework.sample.web.user.dao;

import tech.iboot.framework.sample.web.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/11/17
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@Repository
public class UserCrudDaoImpl implements UserCrudDao {
    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findByIds(Collection<String> ids) {
        return Collections.singletonList(new User(ids.stream().findFirst().isPresent() ? ids.stream().findFirst().get() : "0", "罗小侠", 18));
    }

    @Override
    public List<User> findList() {
        return null;
    }

    @Override
    public Long findCount() {
        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }

    @Override
    public void insertBatch(Collection<User> t) {

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteBatchByIds(Collection<String> ids) {

    }
}
