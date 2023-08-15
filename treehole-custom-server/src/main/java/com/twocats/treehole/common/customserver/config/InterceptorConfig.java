package com.twocats.treehole.common.customserver.config;

import com.twocats.treehole.common.customserver.interceptor.InfoCollectInterceptor;
import com.twocats.treehole.common.customserver.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 配置所有拦截器
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-04-05
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private InfoCollectInterceptor infoCollectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/capi/**");
        registry.addInterceptor(infoCollectInterceptor).addPathPatterns("/capi/**");
    }
}
