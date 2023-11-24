package com.rxlxr.aliyun.mqtt;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rxlxr.aliyun.entities.Content;
import com.rxlxr.aliyun.entities.Param;
import com.rxlxr.aliyun.entities.Product;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.*;

import java.util.UUID;
public class PubAndSub {

    private Product product;
    private MqttSign sign;
    public MqttClient client;
    public Content content;

    public PubAndSub(Product p){
        this.product = p;
    }
    public void connect() throws Exception {

        this.sign = new MqttSign();
        sign.calculate(product.getProductKey(), product.getDeviceName(), product.getDeviceSecret());
        MemoryPersistence persistence = new MemoryPersistence();

        System.out.println("username: " + sign.getUsername());
        System.out.println("password: " + sign.getPassword());
        System.out.println("clientid: " + sign.getClientid());
        content = new Content("default","0",new Param(),"0");
        try {
            // Paho MQTT客户端。
            this.client = new MqttClient(product.getBroker(), sign.getClientid(), persistence);

            // Paho MQTT连接参数。
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval(180);
            connOpts.setUserName(sign.getUsername());
            connOpts.setPassword(sign.getPassword().toCharArray());
            client.connect(connOpts);
            System.out.println("Broker: " + product.getBroker() + " Connected");

        }catch (MqttException e){
            System.out.println("reason " + e.getReasonCode());
            System.out.println("msg " + e.getMessage());
            System.out.println("loc " + e.getLocalizedMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("excep " + e);
            e.printStackTrace();
        }

    }


    public void subscribe(String topicReply) throws MqttException {
        //String topicReply = "/sys/" + this.ProductKey + "/" + this.DeviceName + "/thing/service/property/set";
        this.client.subscribe(topicReply);
//        System.out.println("subscribe: " + topicReply);
    }

    public void listen(){
        this.client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("connection Lost");

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println("Sub message");
                System.out.println("Topic : " + s);
                System.out.println(new String(mqttMessage.getPayload())); //打印输出消息payLoad
                String payLoad = new String(mqttMessage.getPayload());
                ObjectMapper objectMapper = new ObjectMapper();
                content = objectMapper.readValue(payLoad, Content.class);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
    public void publish(String topic, Param p) throws MqttException {
        //String topic = "/sys/" + this.ProductKey + "/" + this.DeviceName + "/thing/event/property/post";
        String param = JSON.toJSONString(p);
        String id = UUID.randomUUID().toString();
        Content c = new Content("thing.service.property.set",id,p,"1.0.0");
        String content = JSON.toJSONString(c);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(0);
        this.client.publish(topic, message);
        System.out.println("publish: " + content);
    }

    public void disconnect() throws MqttException {
        this.client.disconnect();
        System.out.println("Disconnected");
    }


}
