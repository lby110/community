package com.community.community.dao;

import com.community.community.exception.CustomizeException;
import com.community.community.exception.ICustomizeErrorCode;
import lombok.Data;

@Data
public class ResultDTO {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
    public static ResultDTO errorOf(ICustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(CustomizeException e){
        return errorOf(e.getCode(),e.getMessage());
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("操作成功");
        return resultDTO;
    }
}
