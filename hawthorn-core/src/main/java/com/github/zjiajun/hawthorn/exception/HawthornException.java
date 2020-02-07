package com.github.zjiajun.hawthorn.exception;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 15:30
 */
public class HawthornException extends RuntimeException {

    public HawthornException() {
        super();
    }

    public HawthornException(String message) {
        super(message);
    }

    public HawthornException(String message, Throwable cause) {
        super(message, cause);
    }

    public HawthornException(Throwable cause) {
        super(cause);
    }

}
