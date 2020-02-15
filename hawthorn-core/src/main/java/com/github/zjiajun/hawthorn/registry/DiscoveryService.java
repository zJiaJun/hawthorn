package com.github.zjiajun.hawthorn.registry;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/8 16:45
 *
 * 服务消费者 服务发现
 */
public interface DiscoveryService {

    void subscribe(RegisterInfo registerInfo, NotifyListener notifyListener);

    void unsubscribe(RegisterInfo registerInfo);
}
