package com.community.community.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FIND(20000,"服务器异常！！"),
    TARGET_PARAM_NOT_FIND(20001,"未选中任何问题或评论进行回复"),
    NOT_LOGIN(20003,"用户未登录请先登录"),
    SYSTEM_ERROR(20004,"服务器异常"),
    TYPE_PARAM(20005,"评论类型错误或者不存在"),
    COMMENT_NOT_FIND(20006,"评论不存在"),
    QUESTIONS_NOT_FIND(20007,"回复问题不存在"),
    COMMENT_NOT_EXIST(20008,"评论不存在或已被删除"),
    NOT_FIND_CONTEXT(20009,"评论内容不能为空")
    ;

    private Integer code;
    private String message;


    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
