package tech.iboot.framework.starters.mybatisPlus.core;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import jakarta.validation.constraints.NotNull;
import tech.iboot.framework.core.crud.CrudPrimaryKey;


/**
 * <strong>基础实体泛型类</strong>
 * <p>主键字段</p>
 * Created on 2022/11/12
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public abstract class BaseEntity<T extends Model<T>, N> extends Model<T> {

    /**
     * 主键
     */
    @Version
    @TableId(value = "id")
    @CrudPrimaryKey
    @NotNull
    private N id;

    public N getId() {
        return this.id;
    }

    public void setId(N id) {
        this.id = id;
    }
}
