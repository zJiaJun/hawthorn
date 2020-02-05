package hawthorn.serializer;

import hawthorn.exception.HawthornException;
import hawthorn.serializer.hessian.HessianSerializer;
import hawthorn.serializer.java.JavaSerializer;
import hawthorn.serializer.json.JsonSerializer;
import hawthorn.serializer.protostuff.ProtostuffSerializer;
import hawthorn.serializer.xml.XMLSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 23:08
 */
public class SerializerEngine {

    private static final Map<SerializerEnum, Serializer> serializerMap = new HashMap<>();

    static {
        serializerMap.put(SerializerEnum.JavaSerializer, new JavaSerializer());
        serializerMap.put(SerializerEnum.XmlSerializer,  new XMLSerializer());
        serializerMap.put(SerializerEnum.JSONSerializer, new JsonSerializer());
        serializerMap.put(SerializerEnum.HessianSerializer, new HessianSerializer());
        serializerMap.put(SerializerEnum.ProtostuffSerializer, new ProtostuffSerializer());
    }

    public static <T> byte [] serialize(T obj, String serializeType) {
        SerializerEnum serializerEnum = SerializerEnum.get(serializeType);
        Serializer serializer = serializerMap.get(serializerEnum);
        try {
            return serializer.serialize(obj);
        } catch (Exception e){
            throw new HawthornException(e);
        }
    }


    public static <T> T deserialize(byte [] data, Class<T> clazz, String serializeType) {
        SerializerEnum serializerEnum = SerializerEnum.get(serializeType);
        Serializer serializer = serializerMap.get(serializerEnum);
        try {
            return serializer.deserialize(data, clazz);
        } catch (Exception e) {
            throw new HawthornException(e);
        }
    }

}
