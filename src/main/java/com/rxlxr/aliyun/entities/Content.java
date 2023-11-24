package com.rxlxr.aliyun.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    private String method;
    private String id;
    private Param params;
    private String version;

}
