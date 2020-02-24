package com.github.zjiajun.hawthorn;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/11 21:43
 */
public class HawthornService {

    public static void start() {

        Runtime.getRuntime().addShutdownHook(new Thread(HawthornService::stop, "hawthorn-service-thread"));
    }

    public static void stop() {


    }
}
