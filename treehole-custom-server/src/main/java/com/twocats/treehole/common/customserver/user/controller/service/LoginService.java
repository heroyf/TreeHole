package com.twocats.treehole.common.customserver.user.controller.service;

/**
 * @author frankfyang
 * @date 2023-08-15 23:40
 */
public interface LoginService {

    /**
     * 校验token是否有效，如果有效会返回用户的uid
     *
     * @param token token
     * @return
     */
    Long verifyAndGetUid(String token);
}
