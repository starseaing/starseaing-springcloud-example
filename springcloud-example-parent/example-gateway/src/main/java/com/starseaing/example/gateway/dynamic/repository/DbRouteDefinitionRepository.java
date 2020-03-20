package com.starseaing.example.gateway.dynamic.repository;

import com.starseaing.demo.gateway.model.DynamicRoute;
import com.starseaing.demo.gateway.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>基于数据库的实现自定义动态路由配置（代替默认的InMemoryRouteDefinitionRepository）<p/>
 *
 * <p>存在问题：每次请求都会调用getRouteDefinitions，当网关较多时，会影响请求速度，考虑放到本地变量中，使用消息通知更新。</p>
 *
 * @author chentc
 * @since 2019/12/12
 */
@Component
public class DbRouteDefinitionRepository implements RouteDefinitionRepository {

    /**
     * 用于存储路由信息
     */
    private Flux<RouteDefinition> routes = new Flux<RouteDefinition>() {
        @Override
        public void subscribe(CoreSubscriber<? super RouteDefinition> actual) {

        }
    };

    @Autowired
    private DynamicRouteService dynamicRouteService;

    /**
     * 提供路由信息
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        System.out.println("DbRouteDefinitionRepository getRouteDefinitions 直接读取本地路由 ...");
        return routes;
    }

    /**
     * 刷新路由
     */
    public void refreshRouteDefinitions() {
        System.out.println("DbRouteDefinitionRepository refreshRouteDefinitions 从db刷新路由 ...");

        //从数据库取出配置的路由信息
        List<DynamicRoute> dynamicRoutes = dynamicRouteService.listAll();

        List<RouteDefinition> list = new ArrayList<>(dynamicRoutes.size());
        //把路由转换为RouteDefinition对象并放入list
        dynamicRoutes.forEach(x -> {
            list.add(x.toGatewayRouteDefinition().toRouteDefinition());
        });
        routes = Flux.fromIterable(list);
    }


    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
