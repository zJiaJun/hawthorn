package hawthorn.rmi;

import java.rmi.Naming;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/1/12 15:27
 */
public class ClientMain {

    public static void main(String[] args) throws Exception {
        HelloService helloService = (HelloService) Naming.lookup("rmi://localhost:8808/helloService");
        System.out.println(helloService.sayHello("leon"));
    }
}
