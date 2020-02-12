package com.github.zjiajun.register.zookeeper;

import com.github.zjiajun.hawthorn.config.HawthornConfig;
import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.github.zjiajun.hawthorn.registry.AbstractRegistry;
import com.github.zjiajun.hawthorn.registry.ConsumerInstance;
import com.github.zjiajun.hawthorn.registry.ProviderInstance;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/12 09:17
 */
public class ZookeeperRegistry extends AbstractRegistry {

    private final ZkClient zkClient;

    public ZookeeperRegistry() {
        zkClient = new ZkClient(HawthornConfig.singleton().zkAddress(), 6000, 5000 );
    }

    @Override
    protected void doSubscribe(ConsumerInstance consumerInstance) {


    }

    @Override
    protected void doUnsubscribe(ConsumerInstance consumerInstance) {

    }

    @Override
    protected void doRegister(ProviderInstance providerInstance) {

    }

    @Override
    protected void doUnregister(ProviderInstance providerInstance) {

    }

    @Override
    public void close() {

    }
}
