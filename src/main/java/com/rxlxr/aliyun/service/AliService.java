package com.rxlxr.aliyun.service;

import com.rxlxr.aliyun.dao.AliDao;
import com.rxlxr.aliyun.entities.Param;
import com.rxlxr.aliyun.entities.result;
import com.rxlxr.aliyun.mqtt.PubAndSub;
import com.rxlxr.aliyun.utils.Poisson;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rxlxr.aliyun.AliYunApplication.pubAndSub;


@Slf4j
@Service
public class AliService {
    @Autowired
    private AliDao aliDao;

    public result dataHandle(){
        Param p = aliDao.getContent();

        result r = new result();
        r.setPayload(p.getChartData());
        return r;
    }

    public result imageHandle(){
        Param p = aliDao.getContent();
        result r = new result();
        r.setPayload(p.getImage());
        return r;
    }

    public void sendData(Double a) throws MqttException {
        String topic = "/sys/h63zglUmeGz/server/thing/event/property/post";
        String dataTopic = "/h63zglUmeGz/server/user/data";
        pubAndSub.content.getParams().setChartData(Poisson.poissonArray(a.doubleValue(),200));
        pubAndSub.publish(topic,pubAndSub.content.getParams());
        pubAndSub.publish(dataTopic,pubAndSub.content.getParams());
    }

    public void sendImage(String base64) throws MqttException {
        String topic = "/sys/h63zglUmeGz/server/thing/event/property/post";
        String imageTopic = "/h63zglUmeGz/server/user/image";
        pubAndSub.content.getParams().setImage(base64);
        pubAndSub.publish(topic,pubAndSub.content.getParams());
        pubAndSub.publish(imageTopic,pubAndSub.content.getParams());
    }
}
