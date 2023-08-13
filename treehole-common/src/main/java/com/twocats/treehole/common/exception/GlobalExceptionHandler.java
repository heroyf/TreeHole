package com.twocats.treehole.common.exception;

import com.twocats.treehole.common.common.domain.vo.response.ApiResult;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author frankfyang
 * @date 2023-08-13 21:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult methodArgumentNotValidExceptionExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errorMsg.toString();
        LG.info("validation parameters error！The reason is: {}", message);
        return ApiResult.fail(SysErrorCode.PARAM_VALID.getErrorCode(), message.substring(0, message.length() - 1));
    }
}
