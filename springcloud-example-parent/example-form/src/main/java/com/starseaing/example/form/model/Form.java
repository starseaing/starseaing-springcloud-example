package com.starseaing.example.form.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 表单对象
 *
 * @author chentc
 * @since 2020/3/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("fm_form")
public class Form implements Serializable {

    private static final long serialVersionUID = -954471014290158751L;

    /**
     * 主键
     */
    private String id;

    /**
     * 表单id
     */
    private String formId;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 表单的文本内容
     */
    private String formContent;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 最后修改人id
     */
    private String modifierId;

    /**
     * 行记录创建时间
     */
    private Date createTime;

    /**
     * 行记录修改时间
     */
    private Date modifiedTime;

}
