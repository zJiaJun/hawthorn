package hawthorn.serializer.java;

import hawthorn.exception.HawthornException;
import hawthorn.serializer.Serializer;

import java.io.*;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 15:27
 */
public class JavaSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos)) {
            objectOutputStream.writeObject(obj);
        } catch (Exception e) {
            throw new HawthornException(e);
        }
        return baos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(bais)) {
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new HawthornException(e);
        }
    }


}
