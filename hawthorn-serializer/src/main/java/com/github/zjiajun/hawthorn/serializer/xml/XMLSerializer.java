package com.github.zjiajun.hawthorn.serializer.xml;


import com.github.zjiajun.hawthorn.serializer.Serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 19:10
 */
public class XMLSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(baos);
        xmlEncoder.writeObject(obj);
        xmlEncoder.close();
        return baos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        XMLDecoder xmlDecoder = new XMLDecoder(bais);
        Object object = xmlDecoder.readObject();
        xmlDecoder.close();
        return (T) object;
    }
}
