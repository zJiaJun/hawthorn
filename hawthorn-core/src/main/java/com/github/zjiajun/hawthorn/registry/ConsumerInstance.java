package com.github.zjiajun.hawthorn.registry;

import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/9 11:17
 *
 * 服务消费者注册信息
 */
@Data
public class ConsumerInstance {

    private String remoteApp;

    private String group;

    private String service;

    private String host;

}
