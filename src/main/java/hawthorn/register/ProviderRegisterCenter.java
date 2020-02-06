package hawthorn.register;

import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/6 18:41
 *
 * 服务提供者注册中心方法
 */
public interface ProviderRegisterCenter {

    /**
     * 服务端将服务提供者信息注册到ZK对应的节点下
     * @param serviceMetaData 服务信息
     */
    void registerProvider(final List<Object> serviceMetaData);

    /**
     * 服务端获取服务提供者信息
     * @return Map [服务提供者接口, 服务提供者服务方法列表]
     */
    Map<String, List<Object>> getProviderServiceMap();
}
