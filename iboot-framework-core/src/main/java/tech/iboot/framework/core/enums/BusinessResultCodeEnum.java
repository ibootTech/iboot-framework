package tech.iboot.framework.core.enums;

import tech.iboot.framework.core.messageSource.MsHandler;

/**
 * <strong>响应状态码枚举</strong>
 * <p></p>
 * Created on 2022/10/6
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public enum BusinessResultCodeEnum {
    /**
     * 状态
     */
    SUCCESS(0, "msg.api.success"),
    FAIL(-1, "msg.api.failed"),
    ERROR(500, "msg.api.error");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 信息
     */
    private final String msg;

    /**
     * 信息源处理器
     */
    private final MsHandler msHandle = MsHandler.getInstance();

    /**
     * 构造函数
     * @param code 状态码
     * @param msg 信息
     **/
    BusinessResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msHandle.msg(this.msg);
    }
}
