package tech.iboot.framework.core.enums;

import tech.iboot.framework.core.messageSource.MsHandler;

/**
 * Created on 2022/10/6
 * 响应状态码枚举
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc
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

    private final MsHandler msHandle = MsHandler.getInstance();
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
