package com.github.zjiajun.register.zookeeper;

import com.github.zjiajun.hawthorn.config.HawthornConfig;
import com.github.zjiajun.hawthorn.constants.HawthornConstants;
import com.github.zjiajun.hawthorn.registry.AbstractRegistry;
import com.github.zjiajun.hawthorn.registry.NotifyListener;
import com.github.zjiajun.hawthorn.registry.RegisterInfo;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.ArrayList;
import java.util.List;


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
    protected void doSubscribe(RegisterInfo registerInfo, NotifyListener notifyListener) {
        String serviceTypePath = ZkNodeUtils.buildServiceTypePath(registerInfo, HawthornConstants.PROVIDERS);
        zkClient.subscribeChildChanges(serviceTypePath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {

                List<RegisterInfo> registerInfoList = childrenNodeToRegisterInfo(parentPath, currentChilds);
                notifyListener.notify(registerInfoList);
                log.info("ZookeeperRegistry service list change: path={}, currentChilds={}", parentPath, currentChilds);
            }
        });
    }

    @Override
    protected void doUnsubscribe(RegisterInfo registerInfo) {
        String serviceTypePath = ZkNodeUtils.buildServiceTypePath(registerInfo, HawthornConstants.PROVIDERS);
        zkClient.unsubscribeChildChanges(serviceTypePath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {

            }
        });

    }

    @Override
    protected void doRegister(RegisterInfo registerInfo) {
        String serviceTypePath = ZkNodeUtils.buildServiceTypePath(registerInfo, HawthornConstants.PROVIDERS);
        if (!zkClient.exists(serviceTypePath)) {
            zkClient.createPersistent(serviceTypePath, true);
        }
        zkClient.createEphemeral(ZkNodeUtils.buildCompletePath(registerInfo, HawthornConstants.PROVIDERS), ZkNodeUtils.toData(registerInfo));
    }

    @Override
    protected void doUnregister(RegisterInfo registerInfo) {
        String completePath = ZkNodeUtils.buildCompletePath(registerInfo, HawthornConstants.PROVIDERS);
        if (zkClient.exists(completePath)) {
            zkClient.delete(completePath);
        }
    }

    @Override
    public void close() {
        zkClient.close();
    }

    private List<RegisterInfo> childrenNodeToRegisterInfo(String parentPath, List<String> currentChilds) {
        List<RegisterInfo> registerInfoList = new ArrayList<>();
        if (currentChilds == null) {
            return registerInfoList;
        }
        for (String childNode : currentChilds) {
            String nodePath = parentPath + HawthornConstants.PATH_SEPARATOR + childNode;
            String zkData = zkClient.readData(nodePath, true);
            try {
                RegisterInfo registerInfo = ZkNodeUtils.parseData(zkData);
                registerInfoList.add(registerInfo);
            } catch (Exception e){
                log.warn(String.format("Found malformed urls from ZookeeperRegistry, path=%s", nodePath), e);
            }
        }
        return registerInfoList;
    }
}
