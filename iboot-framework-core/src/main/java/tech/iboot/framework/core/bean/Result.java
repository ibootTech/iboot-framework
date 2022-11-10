package tech.iboot.framework.core.bean;

import tech.iboot.framework.core.enums.BusinessResultCodeEnum;

import java.io.Serializable;

/**
 * Created on 2022/11/6
 * 统一响应实体
 * @author Hong Luo
 * @Email luohong@iboot.tech
 * @Desc 用于请求响应统一返回
 **/
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;
    public Result() {}
    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * Created on 2022/10/6
     * 构建成功响应实体
     * @author Hong Luo
     * @return Result<T>
     **/
    public static <T> Result<T> success() {
        return success(BusinessResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * Created on 2022/10/6
     * 构建成功响应实体
     * @author Hong Luo
     * @param data 数据
     * @return Result<T>
     **/
    public static <T> Result<T> success(T data) {
        return success(data, BusinessResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * Created on 2022/10/6
     * 构建成功响应实体
     * @author Hong Luo
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> success(String msg) {
        return success(null, msg);
    }

    /**
     * Created on 2022/10/6
     * 构建成功响应实体
     * @author Hong Luo
     * @param data 数据
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> success(T data, String msg) {
        return result(BusinessResultCodeEnum.SUCCESS.getCode(), data, msg);
    }

    /**
     * Created on 2022/10/6
     * 构建失败响应实体
     * @author Hong Luo
     * @return Result<T>
     **/
    public static <T> Result<T> fail() {
        return fail(BusinessResultCodeEnum.FAIL.getMsg());
    }

    /**
     * Created on 2022/10/6
     * 构建失败响应实体
     * @author Hong Luo
     * @param data 数据
     * @return Result<T>
     **/
    public static <T> Result<T> fail(T data) {
        return fail(data, BusinessResultCodeEnum.FAIL.getMsg());
    }

    /**
     * Created on 2022/10/6
     * 构建失败响应实体
     * @author Hong Luo
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> fail(String msg) {
        return fail(null, msg);
    }

    /**
     * Created on 2022/10/6
     * 构建失败响应实体
     * @author Hong Luo
     * @param data 数据
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> fail(T data, String msg) {
        return result(BusinessResultCodeEnum.FAIL.getCode(), data, msg);
    }
    
    /**
     * Created on 2022/10/6
     * 构建响应实体
     * @author Hong Luo
     * @param code 状态码
     * @param data 数据
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> result(int code, T data, String msg) {
        Result<T> apiResult = new Result<T>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
