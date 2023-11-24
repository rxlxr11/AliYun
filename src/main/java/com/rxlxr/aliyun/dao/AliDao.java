package com.rxlxr.aliyun.dao;


import com.alibaba.fastjson.JSON;
import com.rxlxr.aliyun.entities.Content;
import com.rxlxr.aliyun.entities.Param;
import com.rxlxr.aliyun.mqtt.PubAndSub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.rxlxr.aliyun.AliYunApplication.pubAndSub;

@Slf4j
@Repository
public class AliDao {

    public Param getContent(){
        Param p = pubAndSub.content.getParams();
        log.warn(JSON.toJSONString(pubAndSub.content.getParams()));
        return p;
    }

}
