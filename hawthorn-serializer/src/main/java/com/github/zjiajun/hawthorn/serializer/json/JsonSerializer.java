package com.github.zjiajun.hawthorn.serializer.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.zjiajun.hawthorn.serializer.Serializer;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 19:21
 */
public class JsonSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
        return JSON.toJSONBytes(obj, SerializerFeature.WriteDateUseDateFormat);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }
}
