package com.starseaing.example.usercenter.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 部门信息表
 *
 * @author chentc
 * @since 2019/12/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("DEPT")
public class Dept implements Serializable {

    private static final long serialVersionUID = 5245796624898903537L;

    /**
     * 部门编号
     */
    private Integer deptno;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 所在地址
     */
    private String loc;

}