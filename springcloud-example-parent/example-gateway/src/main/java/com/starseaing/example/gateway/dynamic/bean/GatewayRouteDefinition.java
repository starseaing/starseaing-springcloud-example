package com.starseaing.example.gateway.dynamic.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 路由模型
 *
 * @author chentc
 * @since 2019/12/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRouteDefinition {
    /**
     * 路由的Id
     */
    private String id = UUID.randomUUID().toString();

    /**
     * 路由断言集合配置
     */
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    /**
     * 路由过滤器集合配置
     */
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private int order = 0;

    /**
     * 把自定义的路由模型对象 转换为 路由对象
     * @return
     */
    public RouteDefinition toRouteDefinition() {
        GatewayRouteDefinition route = this;
        RouteDefinition definition = new RouteDefinition();
        definition.setId(route.getId());
        definition.setOrder(route.getOrder());

        //设置断言
        List<PredicateDefinition> pdList = new ArrayList<>();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = route.getPredicates();
        for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        definition.setPredicates(pdList);

        //设置过滤器
        List<FilterDefinition> filters = new ArrayList<>();
        List<GatewayFilterDefinition> gatewayFilters = route.getFilters();
        for (GatewayFilterDefinition filterDefinition : gatewayFilters) {
            FilterDefinition filter = new FilterDefinition();
            filter.setName(filterDefinition.getName());
            filter.setArgs(filterDefinition.getArgs());
            filters.add(filter);
        }
        definition.setFilters(filters);

        URI uri;
        if (route.getUri().startsWith("http")) {
            uri = UriComponentsBuilder.fromHttpUrl(route.getUri()).build().toUri();
        } else {
            // uri为 lb://consumer-service 时使用下面的方法
            uri = URI.create(route.getUri());
        }
        definition.setUri(uri);
        return definition;
    }

}

