package hawthorn.serializer;

import java.io.IOException;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 15:07
 */
public interface Serializer {


    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return byte []
     */
    <T> byte [] serialize(T obj);


    /**
     * 反序列化
     * @param data
     * @param clazz
     * @param <T>
     * @return T
     */
    <T> T deserialize(byte [] data, Class<T> clazz);
}
