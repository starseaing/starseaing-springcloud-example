package com.starseaing.example.gateway.dynamic.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由断言模型
 *
 * @author chentc
 * @since 2019/12/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
