package com.community.community.exception;

public interface ICustomizeErrorCode {
     /**
      * 获取信息
      * @return
      */
     String getMessage();

     /**
      * 获取错误状态码
      * @return
      */
     Integer getCode();
}
