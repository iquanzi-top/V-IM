package com.v.im.common.exception;

/**
 * @author zkp
 */
public enum ResultCodeEnum implements BaseErrorInfoInterface  {

    // 数据操作错误定义
    SUCCESS("200", "成功"),
    BODY_NOT_MATCH("400","请求的数据格式不符"),
    SIGNATURE_NOT_MATCH("401","没有权限"),
    NULL_POINTER("402","空指针异常"),
    NOT_FOUND("404", "未找到该资源"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    SERVER_BUSY("503","服务器正忙，请稍后再试"),
    SMS_ERROR_TIME("601","发送验证码太频繁，请稍后重试"),
    SMS_ERROR_REGISTER("602","此手机已经被注册"),
    SAME_PHONE("603","手机号重复"),
    CODE_ERROR("604","验证码错误"),
    FILE_SIZE_ERROR("900","附件不能超过2M"),
    FILE_TYPE_ERROR("901","文件类型不支持"),
    FILE_UPLOAD_ERROR("911","上传错误"),
    ;

    /**
     * 错误码
     */
    private final String resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ResultCodeEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}
