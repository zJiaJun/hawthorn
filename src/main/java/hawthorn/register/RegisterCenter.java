package hawthorn.register;

import java.util.List;
import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/6 18:57
 */
public class RegisterCenter implements ProviderRegisterCenter, InvokerRegisterCenter {


    @Override
    public void registerProvider(List<Object> serviceMetaData) {


    }

    @Override
    public Map<String, List<Object>> getProviderServiceMap() {
        return null;
    }



    @Override
    public void initProviderMap() {

    }

    @Override
    public Map<String, List<Object>> getServiceMetaDataMap() {
        return null;
    }

    @Override
    public void registerInvoker() {

    }


}
