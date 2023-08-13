package com.twocats.treehole.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twocats.treehole.common.exception.BizException;
import com.twocats.treehole.common.exception.SysErrorCode;

/**
 * @author jkguo
 *         create at 2021/4/4
 **/
public class JsonUtil {

    private JsonUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final ObjectMapper objMapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
      throw new BizException(
              SysErrorCode.PARAM_VALID.getErrorCode(), "toJson failed: " + e.getMessage(), e);
        }
    }

    public static <T> T fromJson(String data, Class<T> rawType) {
        try {
            return objMapper.readValue(data, rawType);
        } catch (JsonProcessingException e) {
            throw new BizException(SysErrorCode.PARAM_VALID.getErrorCode(), "fromJson failed: " + e.getMessage(), e);
        }
    }

    public static <T> T fromJson(String data, TypeReference<T> reference) {
        try {
            return objMapper.readValue(data, reference);
        } catch (JsonProcessingException e) {
            throw new BizException(
                    SysErrorCode.PARAM_VALID.getErrorCode(), "fromJson failed: " + e.getMessage(), e);
        }
    }
}
