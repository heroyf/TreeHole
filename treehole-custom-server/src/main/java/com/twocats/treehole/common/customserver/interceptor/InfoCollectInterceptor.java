package com.twocats.treehole.common.customserver.interceptor;

import cn.hutool.extra.servlet.ServletUtil;
import com.twocats.treehole.common.common.domain.dto.RequestContext;
import com.twocats.treehole.common.common.domain.dto.RequestInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 信息采集拦截器，主要是在记录客户端的client信息
 *
 * @author frankfyang
 * @date 2023-08-15 17:37
 */
@Order(1)
@Component
public class InfoCollectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestInfo info = new RequestInfo();
        info.setClientIp(ServletUtil.getClientIP(request));
        RequestContext.set(info);
        return true;
    }
}
