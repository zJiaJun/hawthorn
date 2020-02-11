package com.github.zjiajun.hawthorn.registry;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/11 21:47
 */
public interface Registry extends RegistryService, DiscoveryService {

    void close();

}
