package com.twocats.treehole.common.customserver.aspect;

import cn.hutool.core.date.StopWatch;
import cn.hutool.json.JSONUtil;
import com.twocats.treehole.common.common.domain.dto.RequestContext;
import com.twocats.treehole.common.common.domain.dto.RequestInfo;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author frankfyang
 * @date 2023-08-13 21:49
 */
@Aspect
@Component
public class ControllerAccLogAspect {

    private static final Logger LG = LoggerFactory.getLogger(ControllerAccLogAspect.class);
    public static final String START_EXECUTE_TIMESTAMP_KEY = "start_execute_timestamp";


    @Pointcut("execution(* com..controller..*.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        // 如果参数有HttpRequest, ServletResponse，直接移除，不打印这些
        List<Object> paramList =
                Stream.of(joinPoint.getArgs())
                        .filter(args -> !(args instanceof ServletRequest))
                        .filter(args -> !(args instanceof ServletResponse))
                        .collect(Collectors.toList());
        RequestInfo requestInfo = RequestContext.get();
        String logRequestInfo = JSONUtil.toJsonStr(requestInfo);
        String logParam = JSONUtil.toJsonStr(paramList);
        if (LG.isInfoEnabled()) {
            LG.info("{} {}, req info: {}, req param: {}", method, uri, logRequestInfo, logParam);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        long cost = stopWatch.getTotalTimeMillis();
        String logResult = JSONUtil.toJsonStr(result);
        if (LG.isInfoEnabled()) {
            LG.info("{} cost: {}ms, response: {}", uri, cost, logResult);
        }
        return result;
    }
}
