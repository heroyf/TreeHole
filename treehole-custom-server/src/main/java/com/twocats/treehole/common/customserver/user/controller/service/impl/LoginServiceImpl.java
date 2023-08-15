package com.twocats.treehole.common.customserver.user.controller.service.impl;

import cn.hutool.core.util.StrUtil;
import com.twocats.treehole.common.customserver.user.controller.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @author frankfyang
 * @date 2023-08-15 23:42
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Long verifyAndGetUid(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return 123L;
    }
}
