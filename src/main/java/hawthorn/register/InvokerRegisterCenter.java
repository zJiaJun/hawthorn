package hawthorn.register;

import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/6 18:45
 *
 * 服务调用者注册中心方法
 */
public interface InvokerRegisterCenter {

    /**
     *
     * 消费端初始化服务提供者信息到本地缓存
     */
    void initProviderMap();

    /**
     * 消费端获取服务提供者信息
     * @return Map [服务提供者接口, 服务提供者服务方法列表]
     */
    Map<String, List<Object>> getServiceMetaDataMap();

    /**
     * 消费端将消费者信息注册到ZK对应的节点下
     */
    void registerInvoker();
}
