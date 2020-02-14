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
public class RegisterInfo {

    /**
     * 应用名称
     */
    private String app;

    /**
     * 分组-组名称
     */
    private String group;

    /**
     * 服务接口全限定类名
     */
    private String service;

    /**
     * 服务地址
     */
    private String host;

    /**
     * 服务端口
     */
    private int port;

}
