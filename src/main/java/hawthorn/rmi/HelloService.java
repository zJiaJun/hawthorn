package hawthorn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/1/12 15:23
 */
public interface HelloService extends Remote {

    String sayHello(String msg) throws RemoteException;
}
