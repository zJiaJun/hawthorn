package com.github.zjiajun.hawthorn.serializer.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.github.zjiajun.hawthorn.exception.HawthornException;
import com.github.zjiajun.hawthorn.serializer.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2020/2/5 19:52
 */
public class HessianSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output hessianOutput = new Hessian2Output(baos);
        byte[] bytes;
        try {
            hessianOutput.writeObject(obj);
            hessianOutput.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            throw new HawthornException(e);
        } finally {
            try {
                hessianOutput.close();
            } catch (IOException e) {
                //ignore
            }
            try {
                baos.close();
            } catch (IOException e) {
                //ignore
            }
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Hessian2Input hessianInput = new Hessian2Input(bais);
        Object object;
        try {
            object = hessianInput.readObject();
        } catch (IOException e) {
            throw new HawthornException(e);
        } finally {
            try {
                hessianInput.close();
            } catch (IOException e) {
                //ignore
            }
        }
        return (T) object;
    }
}
