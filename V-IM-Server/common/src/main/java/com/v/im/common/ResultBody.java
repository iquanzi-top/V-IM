package com.v.im.common;

import com.alibaba.fastjson.JSONObject;

import com.v.im.common.exception.BaseErrorInfoInterface;
import com.v.im.common.exception.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zkp
 */
@Data
public class ResultBody<T> implements Serializable {

    public ResultBody() {
    }

    /**
     * 返回消息代码 默认OK，错误自定义
     */
    private String code = ReturnCode.SUCCESS;

    /**
     * 消息提醒内容
     */
    private String message;

    /**
     * 结果
     */
    private T result;


    /**
     * 成功
     * @param data res
     * @return ReturnMessage
     */
    public ResultBody<T> success(T data) {
        ResultBody<T> rb = new ResultBody<>();
        rb.setCode(ResultCodeEnum.SUCCESS.getResultCode());
        rb.setMessage(ResultCodeEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public ResultBody<T> error(BaseErrorInfoInterface errorInfo) {
        ResultBody<T> rb = new ResultBody<>();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
