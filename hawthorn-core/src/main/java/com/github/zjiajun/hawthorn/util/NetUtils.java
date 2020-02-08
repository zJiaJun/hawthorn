package com.github.zjiajun.hawthorn.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/8 16:29
 */
@Slf4j
public final class NetUtils {


    public static final String LOCALHOST = "127.0.0.1";

    public static final String ANYHOST = "0.0.0.0";

    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static volatile InetAddress LOCAL_ADDRESS = null;


    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }

        InetAddress localAddress = getLocalAddressByHostname();
        if (!isValidAddress(localAddress)) {
            localAddress = getLocalAddressByNetworkInterface();
        }

        if (isValidAddress(localAddress)) {
            LOCAL_ADDRESS = localAddress;
        }
        return localAddress;
    }

    private static InetAddress getLocalAddressByHostname() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            if (isValidAddress(inetAddress)) {
                return inetAddress;
            }
        } catch (Exception e) {
            log.error("Failed to query ip address" + e.getMessage(), e);
        }
        return null;
    }
    private static InetAddress getLocalAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface network = networkInterfaces.nextElement();
                    Enumeration<InetAddress> inetAddresses = network.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress address = inetAddresses.nextElement();
                        if (isValidAddress(address)) {
                            return address;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to query ip address" + e.getMessage(), e);
        }
        return null;
    }

    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) return false;
        String hostAddress = address.getHostAddress();
        return (hostAddress != null && !ANYHOST.equals(hostAddress) && !LOCALHOST.equals(hostAddress) && IP_PATTERN.matcher(hostAddress).matches());
    }

}
