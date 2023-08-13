package com.twocats.treehole.common.customserver.user.controller;

import com.twocats.treehole.common.common.domain.vo.response.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author frankfyang
 * @date 2023-08-13 23:22
 */
@RestController
@RequestMapping("/capi/user")
public class UserController {
    @PostMapping("/test")
    @ApiOperation("用户聚合信息-返回的代表需要刷新的")
    public ApiResult getSummeryUserInfo() {
        return ApiResult.success();
    }
}
