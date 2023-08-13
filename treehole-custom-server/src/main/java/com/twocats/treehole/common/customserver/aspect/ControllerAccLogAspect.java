package com.twocats.treehole.common.customserver.aspect;
import com.twocats.treehole.common.util.JsonUtil;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
    public static final String TRACE_ID_KEY = "traceId";
    public static final String START_EXECUTE_TIMESTAMP_KEY = "startExecuteTimestamp";

    @Pointcut("execution(* com..controller..*.*(..))")
    public void controllerAspect() {
    }


    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        request.setAttribute(START_EXECUTE_TIMESTAMP_KEY, System.currentTimeMillis());
        String method = request.getMethod();
        String uri = request.getRequestURI();
        //如果参数有HttpRequest, ，直接移除，不打印这些
        List<Object> paramList = Stream.of(joinPoint.getArgs())
                .filter(args -> !(args instanceof ServletRequest))
                .filter(args -> !(args instanceof ServletResponse))
                .collect(Collectors.toList());
        if (LG.isInfoEnabled()) {
            LG.info("[{} - {}][request: {}]", method, uri, JsonUtil.toJson(paramList));
        }
    }

    @AfterReturning(pointcut = "controllerAspect()", returning = "returnVal")
    public void doAfterReturning(JoinPoint joinPoint, Object returnVal) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String uri = request.getRequestURI();
        long start = (long) request.getAttribute(START_EXECUTE_TIMESTAMP_KEY);
        long end = System.currentTimeMillis();
        LG.info("[{}][cost:{}ms][response: {}]", uri, end - start, JsonUtil.toJson(returnVal));
        MDC.put(TRACE_ID_KEY, "");
    }
}
