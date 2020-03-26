package com.starseaing.example.form.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("fm_form_data")
public class FormData implements Serializable {

        private static final long serialVersionUID = 1553082688327972526L;

        /**
         * 主键
         */
        private String id;

        /**
         * 业务id
         */
        private String businessId;

        /**
         * 表单id
         */
        private String formId;

        /**
         * 表单数据id
         */
        private String dataId;

        /**
         * 表单的json字符串数据
         */
        private String jsonData;

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

