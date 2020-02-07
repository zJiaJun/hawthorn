package com.github.zjiajun.hawthorn.util

import java.net.InetAddress
import java.util.regex.Pattern

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/7 20:58
 */
object NetUtils {

  val ANYHOST = "0.0.0.0"
  val LOCALHOST = "127.0.0.1"
  val IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$")

  def isValidAddress(address: InetAddress): Boolean = {
    if (address == null || address.isLoopbackAddress) return false
    val name = address.getHostAddress
    (name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name) && IP_PATTERN.matcher(name).matches())
  }

}