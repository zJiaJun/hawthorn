package com.github.zjiajun.hawthorn.registry;

import java.util.Objects;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/11 21:48
 */
public abstract class AbstractRegistry implements Registry {

    @Override
    public void subscribe(RegisterInfo registerInfo, NotifyListener notifyListener) {
        Objects.requireNonNull(registerInfo, "Registry subscribe param registerInfo is null");
        doSubscribe(registerInfo, notifyListener);
    }

    @Override
    public void unsubscribe(RegisterInfo registerInfo, NotifyListener notifyListener) {
        Objects.requireNonNull(registerInfo, "Registry unsubscribe param registerInfo is null");
        doUnsubscribe(registerInfo, notifyListener);
    }

    @Override
    public void register(RegisterInfo registerInfo) {
        Objects.requireNonNull(registerInfo, "Registry register param registerInfo is null");
        doRegister(registerInfo);
    }

    @Override
    public void unregister(RegisterInfo registerInfo) {
        Objects.requireNonNull(registerInfo, "Registry unregister param registerInfo is null");
        doUnregister(registerInfo);
    }

    protected abstract void doSubscribe(RegisterInfo registerInfo, NotifyListener notifyListener);

    protected abstract void doUnsubscribe(RegisterInfo registerInfo, NotifyListener notifyListener);

    protected abstract void doRegister(RegisterInfo registerInfo);

    protected abstract void doUnregister(RegisterInfo registerInfo);
}
