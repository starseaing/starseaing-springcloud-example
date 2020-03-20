package com.starseaing.example.gateway.dynamic.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器模型
 *
 * @author chentc
 * @since 2019/12/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayFilterDefinition {
    /**
     * 过滤器名称
     */
    private String name;


    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
