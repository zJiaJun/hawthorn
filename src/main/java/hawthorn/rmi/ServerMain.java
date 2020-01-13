package hawthorn.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/1/12 15:25
 */
public class ServerMain {

    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        LocateRegistry.createRegistry(8808);
        Naming.bind("rmi://localhost:8808/helloService", helloService);


    }
}
