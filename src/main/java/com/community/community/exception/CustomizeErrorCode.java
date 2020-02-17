package com.community.community.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FIND("服务器异常！！");
    ;
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
