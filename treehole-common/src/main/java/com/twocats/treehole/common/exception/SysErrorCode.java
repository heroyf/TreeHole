package com.twocats.treehole.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author frankfyang
 * @date 2023-08-13 21:41
 */
@AllArgsConstructor
@Getter
public enum SysErrorCode implements ErrorCode{
    SYSTEM_ERROR(-1, "系统开小差了，请稍后再试哦~"),
    PARAM_VALID(-2, "参数非法{0}"),
    FREQUENCY_LIMIT(-3, "系统扛不住啦，请稍后再试哦~"),
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
