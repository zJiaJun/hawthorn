package hawthorn.serializer.protostuff;

import hawthorn.exception.HawthornException;
import hawthorn.serializer.Serializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 20:39
 */
public class ProtostuffSerializer implements Serializer {

    private static Map<Class<?>, Schema<?>> cacheSchemaMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    private <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cacheSchemaMap.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cacheSchemaMap.put(clazz, schema);
        }
        return schema;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        Schema<T> schema = getSchema(clazz);
        LinkedBuffer allocate = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(obj, schema, allocate);
        } catch (Exception e) {
            throw new HawthornException(e);
        } finally {
            allocate.clear();
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, instance, schema);
            return instance;
        } catch (Exception e) {
            throw new HawthornException(e);
        }
    }


}
