package com.starseaing.example.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.starseaing.example.gateway.dynamic.repository.DbRouteDefinitionRepository;
import com.starseaing.example.gateway.dynamic.service.DynamicRoutePublishService;
import com.starseaing.example.gateway.model.DynamicRoute;
import com.starseaing.example.gateway.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * 实现动态路由功能的rest接口
 *
 * @author chentc
 * @since 2019/12/10
 */
@RestController
@RequestMapping("/route")
public class DynamicRouteController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Autowired
    private DynamicRoutePublishService dynamicRoutePublishService;

    @Autowired
    private DbRouteDefinitionRepository dbRouteDefinitionRepository;

    /**
     * 增加路由
     *
     * @param dynamicRoute
     * @return
     */
    @PostMapping("/add")
    public JSONObject add(@RequestBody DynamicRoute dynamicRoute) {
        int count = dynamicRouteService.insert(dynamicRoute);

        dbRouteDefinitionRepository.refreshRouteDefinitions();

        JSONObject result = new JSONObject();
        result.put("addDb", count);
        result.put("addGateway", dbRouteDefinitionRepository.getRouteDefinitions());
        return result;
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    @DeleteMapping("/routes/{id}")
    public JSONObject delete(@PathVariable String id) {
        int count = dynamicRouteService.delete(id);

        dbRouteDefinitionRepository.refreshRouteDefinitions();

        JSONObject result = new JSONObject();
        result.put("deleteDb", count);
        result.put("deleteGateway", dbRouteDefinitionRepository.getRouteDefinitions());
        return result;
    }

    /**
     * 更新路由
     *
     * @param dynamicRoute
     * @return
     */
    @PostMapping("/update")
    public JSONObject update(@RequestBody DynamicRoute dynamicRoute) {
        int count = dynamicRouteService.update(dynamicRoute);

        dbRouteDefinitionRepository.refreshRouteDefinitions();

        JSONObject result = new JSONObject();
        result.put("updateDb", count);
        result.put("updateGateway", dbRouteDefinitionRepository.getRouteDefinitions());
        return result;
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public IPage<DynamicRoute> page(@RequestParam(required = false) DynamicRoute dynamicRoute,
                                    @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "10") int pageSize) {
        IPage<DynamicRoute> pageList = dynamicRouteService.pageList(pageNumber, pageSize, dynamicRoute);
        return pageList;
    }

    /**
     * 查询网关当前的所有路由
     *
     * @return
     */
    @GetMapping("/list")
    public Flux<RouteDefinition> list() {
        Flux<RouteDefinition> routeDefinitions = dbRouteDefinitionRepository.getRouteDefinitions();
        return routeDefinitions;
    }

    /**
     * 刷新网关的路由
     *
     * @return
     */
    @GetMapping("/refresh")
    public Flux<RouteDefinition> refresh() {
        dbRouteDefinitionRepository.refreshRouteDefinitions();
        return dbRouteDefinitionRepository.getRouteDefinitions();
    }
}
