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

    @PostMapping("/private/test")
    @ApiOperation("私有测试接口")
    public ApiResult privateTest() {
        return ApiResult.success();
    }

    @PostMapping("/public/test")
    @ApiOperation("公开测试接口")
    public ApiResult publicTest() {
        return ApiResult.success();
    }
}
