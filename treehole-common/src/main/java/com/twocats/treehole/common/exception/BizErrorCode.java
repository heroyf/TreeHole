package com.twocats.treehole.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author frankfyang
 * @date 2023-08-13 21:40
 */
@AllArgsConstructor
@Getter
public enum BizErrorCode implements ErrorCode{
    
    ;
    private final Integer code;
    private final String msg;

    @Override
    public Integer getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMsg() {
        return this.msg;
    }
}
