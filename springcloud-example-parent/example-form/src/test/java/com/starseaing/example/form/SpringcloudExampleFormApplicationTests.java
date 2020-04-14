package com.starseaing.example.form;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.starseaing.example.form.model.FormData;
import com.starseaing.example.form.service.FormDataService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class SpringcloudExampleFormApplicationTests {

    @Autowired
    private FormDataService formDataService;

    @Test
    void contextLoads() {

    }
    @Test
    public void test1(){
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            Date now = new Date();
            JSONObject formDataObj = new JSONObject();
            formDataObj.put("businessId", UUID.randomUUID());
            formDataObj.put("formId", "form-test");

            JSONObject formData = new JSONObject();
            formData.put("date", now);
            formData.put("key", UUID.randomUUID());
            formDataObj.put("jsonData", JSONObject.toJSONString(formData, true));

            String id = UUID.randomUUID().toString();
            formDataObj.put("dataId", "data-" + String.valueOf(i));
            formDataObj.put("id", id);
            formDataObj.put("creatorId", "admin");
            formDataObj.put("modifierId", "admin");
            formDataObj.put("createTime", now);
            formDataObj.put("modifiedTime", now);

            FormData formDataBean = JSONObject.toJavaObject(formDataObj, FormData.class);

            formDataService.insert(formDataBean);

            count ++;
        }
        Assert.assertEquals(1000000, count);

    }

}
