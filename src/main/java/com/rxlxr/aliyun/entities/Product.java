package com.rxlxr.aliyun.entities;

import lombok.Data;


@Data
public class Product {
    private String ProductKey;
    private String ProductSecret;
    private String DeviceSecret;
    private String DeviceName;

    private String port = "1883";
    private String broker ;

    public Product (String productKey,String productSecret,String deviceSecret,String deviceName){
        this.ProductKey = productKey;
        this.ProductSecret = productSecret;
        this.DeviceSecret = deviceSecret;
        this.DeviceName = deviceName;
        this.broker = "ssl://" + this.ProductKey + ".iot-as-mqtt.cn-shanghai.aliyuncs.com" + ":" + port;
    }
}
