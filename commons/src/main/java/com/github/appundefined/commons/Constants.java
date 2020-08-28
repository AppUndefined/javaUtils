package com.github.appundefined.commons;

import java.io.Serializable;

/**
 * 封装常量对象
 * @author EDZ
 */
public class Constants implements Serializable {

    /**
     * 状态：正常
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 状态：未找到
     */
    public static final Integer NOT_FIND_CODE = 404;
    /**
     * 状态：未知错误
     */
    public static final Integer ERROR_CODE = 500;
    /**
     * 操作成功
     */
    public static final String SUCCESS = "操作成功";
    /**
     * 未找到数据
     */
    public static final String NOT_FIND = "未找到数据";
    /**
     * 操作失败
     */
    public static final String ERROR = "操作失败";




}
