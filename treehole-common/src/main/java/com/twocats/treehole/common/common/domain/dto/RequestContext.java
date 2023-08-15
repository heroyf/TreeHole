package com.twocats.treehole.common.common.domain.dto;

/**
 * @author frankfyang
 * @date 2023-08-15 18:59
 */
public class RequestContext {

    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
