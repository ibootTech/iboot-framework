package tech.iboot.framework.core.bean;

import tech.iboot.framework.core.enums.BusinessResultCodeEnum;

import java.io.Serializable;

/**
 * <strong>统一响应实体</strong>
 * <p>用于请求响应统一返回</p>
 * Created on 2022/10/6
 *
 * @author <a href="mailto:luohong@iboot.tech">Hong Luo</a>
 **/
public class Result<T> implements Serializable {
    /**
     * 序列化
     */
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

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  信息
     **/
    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 构建成功响应实体
     *
     * @return Result<T>
     **/
    public static <T> Result<T> success() {
        return success(BusinessResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 构建成功响应实体
     *
     * @param data  数据
     * @return Result<T>
     **/
    public static <T> Result<T> success(T data) {
        return success(data, BusinessResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 构建成功响应实体
     *
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> success(String msg) {
        return success(null, msg);
    }

    /**
     * 构建成功响应实体
     *
     * @param data 数据
     * @param msg  信息
     * @return Result<T>
     **/
    public static <T> Result<T> success(T data, String msg) {
        return result(BusinessResultCodeEnum.SUCCESS.getCode(), data, msg);
    }

    /**
     * 构建失败响应实体
     *
     * @return Result<T>
     **/
    public static <T> Result<T> fail() {
        return fail(BusinessResultCodeEnum.FAIL.getMsg());
    }

    /**
     * 构建失败响应实体
     *
     * @param data 数据
     * @return Result<T>
     **/
    public static <T> Result<T> fail(T data) {
        return fail(data, BusinessResultCodeEnum.FAIL.getMsg());
    }

    /**
     * 构建失败响应实体
     *
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> fail(String msg) {
        return fail(null, msg);
    }

    /**
     * 构建失败响应实体
     *
     * @param data 数据
     * @param msg 信息
     * @return Result<T>
     **/
    public static <T> Result<T> fail(T data, String msg) {
        return result(BusinessResultCodeEnum.FAIL.getCode(), data, msg);
    }

    /**
     * 构建响应实体
     *
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
