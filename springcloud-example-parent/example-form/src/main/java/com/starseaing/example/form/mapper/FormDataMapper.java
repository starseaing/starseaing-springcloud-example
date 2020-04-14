package com.starseaing.example.form.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starseaing.example.form.model.FormData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author chentc
 * @since 2020/3/23
 */
@Repository
public interface FormDataMapper extends BaseMapper<FormData> {

    /**
     * 批量出入
     * @param formDataList
     * @return
     */
    int batchInsert(List<FormData> formDataList);

}