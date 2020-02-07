package com.github.zjiajun.hawthorn.serializer;

import com.github.zjiajun.hawthorn.exception.HawthornException;
import com.github.zjiajun.hawthorn.serializer.hessian.HessianSerializer;
import com.github.zjiajun.hawthorn.serializer.java.JavaSerializer;
import com.github.zjiajun.hawthorn.serializer.json.JsonSerializer;
import com.github.zjiajun.hawthorn.serializer.protostuff.ProtostuffSerializer;
import com.github.zjiajun.hawthorn.serializer.xml.XMLSerializer;

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
