package com.github.zjiajun.hawthorn.registry;

import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/9 11:17
 *
 * 服务提供者注册信息
 */
@Data
public class ProviderInstance {

    private String app;

    private String group;

    private String service;

    private String host;

    private int port;

}