package com.starseaing.example.usercenter.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 雇员工资表
 *
 * @author chentc
 * @since 2019/12/12
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("BONUS")
public class Bonus implements Serializable {

    private static final long serialVersionUID = -1819824810384084288L;

    /**
     * 雇员的姓名
     */
    private String ename;

    /**
     * 雇员的职位
     */
    private String job;

    /**
     * 雇员的工资
     */
    private BigDecimal sal;

    /**
     * 雇员的资金
     */
    private BigDecimal comm;

}
