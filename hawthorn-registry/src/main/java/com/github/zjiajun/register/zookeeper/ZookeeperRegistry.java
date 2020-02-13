package com.github.zjiajun.register.zookeeper;

import com.github.zjiajun.hawthorn.config.HawthornConfig;
import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.github.zjiajun.hawthorn.registry.AbstractRegistry;
import com.github.zjiajun.hawthorn.registry.ConsumerInstance;
import com.github.zjiajun.hawthorn.registry.ProviderInstance;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/12 09:17
 *
 *  zkNode
 *  /hawthorn/app/group/serviceName/providers/ip+port
 *  /hawthorn/app/group/serviceName/consumers/ip
 */
@Slf4j
public class ZookeeperRegistry extends AbstractRegistry {

    private final ZkClient zkClient;

    public ZookeeperRegistry() {
        zkClient = new ZkClient(HawthornConfig.singleton().zkAddress(),
                HawthornConfig.singleton().zkSessionTimeout(),
                HawthornConfig.singleton().zkConnectionTimeout()
        );
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {

            }

            @Override
            public void handleNewSession() throws Exception {
                log.info("zkRegistry get new session notify.");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {

            }
        });

    }

    @Override
    protected void doSubscribe(ConsumerInstance consumerInstance) {


    }

    @Override
    protected void doUnsubscribe(ConsumerInstance consumerInstance) {

    }

    @Override
    protected void doRegister(ProviderInstance providerInstance) {
        String serviceTypePath = ZkNodeUtils.buildServiceTypePath(providerInstance, HawthornConstants.PROVIDERS);
        if (!zkClient.exists(serviceTypePath)) {
            zkClient.createPersistent(serviceTypePath, true);
        }
        zkClient.createEphemeral(ZkNodeUtils.buildCompletePath(providerInstance, HawthornConstants.PROVIDERS), "data?");
    }

    @Override
    protected void doUnregister(ProviderInstance providerInstance) {

    }

    @Override
    public void close() {
        zkClient.close();
    }
}
