package com.twocats.treehole.common.customserver.interceptor;

import com.twocats.treehole.common.customserver.user.controller.service.LoginService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用作用户登录态的拦截器
 *
 * @author frankfyang
 * @date 2023-08-15 19:17
 */
@Order(-1)
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
    public static final String ATTRIBUTE_UID = "uid";
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean isPublicURI = isPublicURI(request.getRequestURI());
        // 非公开路径，对用户的token进行校验
        if (!isPublicURI) {
            // 获取用户的request中的token
            String token = getToken(request);
            // 校验token的合法性
            Long validUid = loginService.verifyAndGetUid(token);
            if (validUid == null) {
                // 如果token不合法，返回401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            // 合法，则设置uid属性
            request.setAttribute(ATTRIBUTE_UID, validUid);
        }
        return true;
    }

    /**
     * 判断是否是公开路径，例如uri /capi/xxx/public/xxx/xxx就是一个可公开的接口，无需登录态也可以访问
     *
     * @param requestURI 请求的uri
     * @return
     */
    private boolean isPublicURI(String requestURI) {
        String[] split = requestURI.split("/");
        return split.length > 2 && "public".equals(split[3]);
    }

    /**
     * 获取request中的token，如果没有返回null
     *
     * @param request 用户请求的request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        return Optional.ofNullable(header)
                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
                .map(h -> h.substring(AUTHORIZATION_SCHEMA.length()))
                .orElse(null);
    }
}
