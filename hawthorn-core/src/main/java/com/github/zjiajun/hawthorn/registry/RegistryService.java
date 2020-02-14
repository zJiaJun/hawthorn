package com.github.zjiajun.hawthorn.registry;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/8 16:44
 *
 * 服务提供者 服务注册
 */
public interface RegistryService {

    void register(RegisterInfo registerInfo);

    void unregister(RegisterInfo registerInfo);

}
