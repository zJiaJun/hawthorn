package com.github.zjiajun.hawthorn.registry;

import java.util.Objects;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/11 21:48
 */
public abstract class AbstractRegistry implements Registry {

    @Override
    public void subscribe(ConsumerInstance consumerInstance) {
        Objects.requireNonNull(consumerInstance, "Registry subscribe param consumerInstance is null");
        doSubscribe(consumerInstance);
    }

    @Override
    public void unsubscribe(ConsumerInstance consumerInstance) {
        Objects.requireNonNull(consumerInstance, "Registry unsubscribe param consumerInstance is null");
        doUnsubscribe(consumerInstance);
    }

    @Override
    public void register(ProviderInstance providerInstance) {
        Objects.requireNonNull(providerInstance, "Registry register param providerInstance is null");
        doRegister(providerInstance);
    }

    @Override
    public void unregister(ProviderInstance providerInstance) {
        Objects.requireNonNull(providerInstance, "Registry unregister param providerInstance is null");
        doUnregister(providerInstance);
    }

    protected abstract void doSubscribe(ConsumerInstance consumerInstance);

    protected abstract void doUnsubscribe(ConsumerInstance consumerInstance);

    protected abstract void doRegister(ProviderInstance providerInstance);

    protected abstract void doUnregister(ProviderInstance providerInstance);
}
