package com.twocats.treehole.common.common.domain.vo.response;

import com.twocats.treehole.common.exception.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frankfyang
 * @date 2023-08-13 21:25
 */
@Data
@ApiModel("基础返回体")
public class ApiResult<T> {
    @ApiModelProperty("成功标识true or false")
    private Boolean success;
    @ApiModelProperty("错误码")
    private Integer errCode;
    @ApiModelProperty("错误消息")
    private String errMsg;
    @ApiModelProperty("traceId")
    private String traceId;
    @ApiModelProperty("返回对象")
    private T data;

    /**
     * 返回成功，data为空
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(null);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    /**
     * 返回成功，data为传入的参数
     * @param data 返回参数
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    /**
     * 返回失败，code和msg为传入的参数
     * @param code 失败错误码
     * @param msg 失败信息
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> fail(Integer code, String msg) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(code);
        result.setErrMsg(msg);
        return result;
    }


    public static <T> ApiResult<T> fail(ErrorCode errorCode) {
        ApiResult<T> result = new ApiResult<T>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(errorCode.getErrorCode());
        result.setErrMsg(errorCode.getErrorMsg());
        return result;
    }


    /**
     * 判断接口是否成功
     * @return 成功返回true，失败返回false
     */
    public boolean isSuccess() {
        return this.success;
    }
}
