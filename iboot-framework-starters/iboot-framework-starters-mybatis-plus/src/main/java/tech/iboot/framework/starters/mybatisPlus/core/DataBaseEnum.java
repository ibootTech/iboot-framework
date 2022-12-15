package tech.iboot.framework.starters.mybatisPlus.core;

/**
 * <strong>数据库类型</strong>
 * <p>mysql</p>
 * Created on 2022/11/13
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public enum DataBaseEnum {

    /**
     * 数据库
     */
    MySql("MySQL", "mysql"),
    DM("DM", "dm");

    /**
     * 名称
     */
    private final String name;

    /**
     * 值
     */
    private final String value;

    DataBaseEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
