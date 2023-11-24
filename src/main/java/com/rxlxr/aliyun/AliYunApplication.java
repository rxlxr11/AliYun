package com.rxlxr.aliyun;

import com.rxlxr.aliyun.entities.Param;
import com.rxlxr.aliyun.entities.Product;
import com.rxlxr.aliyun.mqtt.PubAndSub;
import com.rxlxr.aliyun.utils.Poisson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AliYunApplication {

    public static PubAndSub pubAndSub ;


    public static void main(String[] args) throws Exception{
        SpringApplication.run(AliYunApplication.class, args);
        String ProductKey = "h63zglUmeGz";
        String ProductSecret = "rcBTRtXmdRIdANOF";
        String DeviceSecret = "63cc5edab8b224f4de9d02e6a136bf5d";
        String DeviceName = "server";
        Product product_aliyun = new Product(ProductKey,ProductSecret,DeviceSecret,DeviceName);
        pubAndSub = new PubAndSub(product_aliyun);
        pubAndSub.connect();
        pubAndSub.listen();

    }

}
