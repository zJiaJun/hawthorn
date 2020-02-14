package com.github.zjiajun.hawthorn.registry;

import java.util.List;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/14 19:33
 */
public interface NotifyListener {

    void notify(List<RegisterInfo> registerInfoList);
}
