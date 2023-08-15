package com.twocats.treehole.common.common.domain.dto;

import lombok.Data;

/**
 * @author frankfyang
 * @date 2023-08-15 17:50
 */
@Data
public class RequestInfo {

    /**
     * 用户uid信息
     */
    private Long uid;
    /**
     * 客户端ip
     */
    private String clientIp;
    /**
     * 客户端名称
     */
    private String clientName;
}
