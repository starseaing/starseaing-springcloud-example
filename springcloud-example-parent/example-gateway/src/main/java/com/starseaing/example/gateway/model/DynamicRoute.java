package com.starseaing.example.gateway.model;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.starseaing.demo.gateway.dynamic.bean.GatewayFilterDefinition;
import com.starseaing.demo.gateway.dynamic.bean.GatewayPredicateDefinition;
import com.starseaing.demo.gateway.dynamic.bean.GatewayRouteDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 动态路由信息表
 *
 * @author chentc
 * @since 2019/12/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("SYS_DYNAMIC_ROUTE")
public class DynamicRoute implements Serializable {

    private static final long serialVersionUID = -4002730232055056466L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 路由id
     */
    private String routeId;

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由断言集合
     */
    private String predicates;

    /**
     * 路由过滤器集合
     */
    private String filters;

    /**
     * 路由执行的顺序
     */
    private Integer sortNo;

    /**
     * 状态，0-禁用，1-启用
     */
    private Integer enabled;

    /**
     * 删除状态标识：0-正常，1-已刪除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 数据库的动态路由对象 转换为 自定义的路由模型对象
     * @return
     */
    public GatewayRouteDefinition toGatewayRouteDefinition(){
        GatewayRouteDefinition routeDefinition = GatewayRouteDefinition.builder()
                .id(routeId)
                .uri(uri)
                .order(sortNo)
                .build();
        List<GatewayPredicateDefinition> predicateDefinitions = JSONArray.parseArray(predicates, GatewayPredicateDefinition.class);
        List<GatewayFilterDefinition> filterDefinitions = JSONArray.parseArray(filters, GatewayFilterDefinition.class);

        routeDefinition.setPredicates(predicateDefinitions);
        routeDefinition.setFilters(filterDefinitions);

        return routeDefinition;
    }

}