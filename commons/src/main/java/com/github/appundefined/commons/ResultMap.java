package com.github.appundefined.commons;

import java.io.Serializable;
import java.util.Map;

/**
 * 封装统一返回值对象 此类返回数据想当于一个map
 */

public class ResultMap implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private Object data;

    public ResultMap() {
    }

    public ResultMap(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultMap success(Map<String, Object> variables) {
        return new ResultMap(Constants.SUCCESS_CODE, "操作成功", (Object)null);
    }

    public static ResultMap success(String message) {
        return new ResultMap(Constants.SUCCESS_CODE, message, (Object)null);
    }

    public static ResultMap successData(Object data) {
        if (data == null) {
            data = "";
        }

        return new ResultMap(Constants.SUCCESS_CODE, "操作成功", data);
    }

    public static ResultMap success(String message, Object data) {
        return new ResultMap(Constants.SUCCESS_CODE, message, data);
    }
    public static ResultMap success() {
        return new ResultMap(Constants.SUCCESS_CODE, Constants.SUCCESS,null);
    }
    public static ResultMap error() {
        return new ResultMap(Constants.ERROR_CODE, "操作失败", (Object)null);
    }

    public static ResultMap error(Integer code, String message) {
        return new ResultMap(code, message, (Object)null);
    }

    public static ResultMap success(Integer code, String message) {
        return new ResultMap(code, message, (Object)null);
    }

    public static ResultMap error(String message) {
        return new ResultMap(Constants.ERROR_CODE, message, (Object)null);
    }

    public static ResultMap notFind() {
        return new ResultMap(Constants.NOT_FIND_CODE, "未找到数据", (Object)null);
    }

    public static ResultMap notFind(String message) {
        return new ResultMap(Constants.NOT_FIND_CODE, message, (Object)null);
    }




    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultMap)) {
            return false;
        } else {
            ResultMap other = (ResultMap)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResultMap;
    }


    public String toString() {
        return "ResultMap(code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ")";
    }
}
