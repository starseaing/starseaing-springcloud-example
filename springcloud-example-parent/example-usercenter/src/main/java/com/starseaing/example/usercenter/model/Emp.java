package com.starseaing.example.usercenter.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 雇员信息表
 *
 * @author chentc
 * @since 2019/12/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("EMP")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1806279607849625639L;

    /**
     * 雇员编号
     */
    private Integer empno;

    /**
     * 雇员姓名
     */
    private String ename;

    /**
     * 雇员职位
     */
    private String job;

    /**
     * 雇员对应的领导的编号
     */
    private Integer mgr;

    /**
     * 雇员的雇佣日期
     */
    private Date hiredate;

    /**
     * 雇员的基本工资
     */
    private BigDecimal sal;

    /**
     * 雇员的奖金
     */
    private BigDecimal comm;

    /**
     * 雇员的所在部门
     */
    private Integer deptno;

}
