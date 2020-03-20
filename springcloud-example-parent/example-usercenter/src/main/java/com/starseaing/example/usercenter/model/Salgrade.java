package com.starseaing.example.usercenter.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 工资等级表
 *
 * @author chentc
 * @since 2019/12/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("SALGRADE")
public class Salgrade implements Serializable {

    private static final long serialVersionUID = 2087916771674522297L;

    /**
     * 工资等级
     */
    private Integer grade;

    /**
     * 此等级的最低工资
     */
    private Integer losal;

    /**
     * 此等级的最高工资
     */
    private Integer hisal;
}