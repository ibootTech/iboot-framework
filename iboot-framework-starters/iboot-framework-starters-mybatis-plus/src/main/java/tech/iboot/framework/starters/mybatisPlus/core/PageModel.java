package tech.iboot.framework.starters.mybatisPlus.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <strong>分页</strong>
 * <p></p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class PageModel {

    /**
     * 当前页码
     */
    private int current;

    /**
     * 当前页个数
     */
    private int size;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public <T>IPage<T> page() {
        return new Page<>(this.current, this.size == 0 ? -1 : this.size);
    }

}
