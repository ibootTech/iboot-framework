package tech.iboot.framework.sample.mybatisPlus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tech.iboot.framework.sample.mybatisPlus.user.entity.User;
import tech.iboot.framework.starters.mybatisPlus.annotation.Mapper;
import tech.iboot.framework.starters.mybatisPlus.builder.SqlBuilder;

import java.util.List;

/**
 * <strong></strong>
 * <p></p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 使用原生sql查询列表
     * @param sql 原生sql
     * @param paramList 参数
     * @return List<User>
     */
    @SelectProvider(type = SqlBuilder.class, method = "nativeSql")
    List<User> findListBySql(@Param(SqlBuilder.SQL) String sql, @Param(SqlBuilder.PARAM)Object... paramList);
}
