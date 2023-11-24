package com.rxlxr.aliyun.controller;

import com.rxlxr.aliyun.entities.result;
import com.rxlxr.aliyun.service.AliService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
public class AliController {
    @Autowired
    private AliService service;

    @CrossOrigin
    @RequestMapping("/chartData")
    public result getData(){
        log.info("get chart data");
        result r = service.dataHandle();
        r.setMessage(true);
        return r;
    }

    @CrossOrigin
    @RequestMapping("/imageBase64")
    public result getImage(){
        log.info("get image data");
        result r = service.imageHandle();
        r.setMessage(true);
        return r;
    }

    @CrossOrigin
    @RequestMapping("/poisson")
    public result SetData(@RequestBody Double a) throws MqttException {
        service.sendData(a);
        result r =  new result();
        r.setMessage(true);
        return r;
    }

    @CrossOrigin
    @RequestMapping("/uploadImage")
    public result SetImage(@RequestBody String base64) throws MqttException {
        service.sendImage(base64);
        result r =  new result();
        r.setMessage(true);
        return r;
    }
}
