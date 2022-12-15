package tech.iboot.framework.sample.mybatisPlus.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import tech.iboot.framework.starters.mybatisPlus.core.RichEntity;

/**
 * <strong>用户实体</strong>
 * <p>可查看doc文件夹sql文件，创建表</p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
@Getter
@Setter
@TableName("test_user")
public class User extends RichEntity<User, String> {
    /** 姓名 */
    @Schema(title = "用户名称")
    private String name ;

    /** 账号 */
    private String account ;
}
