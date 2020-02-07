package com.github.zjiajun.hawthorn.serializer;


import com.github.zjiajun.hawthorn.exception.HawthornException;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 23:09
 */
public enum SerializerEnum {

    JavaSerializer("java"),
    XmlSerializer("xml"),
    JSONSerializer("json"),
    HessianSerializer("hessian"),
    ProtostuffSerializer("protostuff"),
    ;

    private String serializeType;


    public String getSerializeType() {
        return serializeType;
    }

    SerializerEnum(String serializeType) {
        this.serializeType = serializeType;
    }

    public static SerializerEnum get(String serializeType) {
        for (SerializerEnum value : SerializerEnum.values()) {
            if (serializeType.equals(value.getSerializeType())) {
                return value;
            }
        }
        throw new HawthornException("SerializeType is wrong");
    }

}
