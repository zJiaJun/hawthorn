package hawthorn.serializer.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import hawthorn.exception.HawthornException;
import hawthorn.serializer.Serializer;

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
        HessianOutput hessianOutput = new HessianOutput(baos);
        try {
            hessianOutput.writeObject(obj);
        } catch (IOException e) {
            throw new HawthornException(e);
        } finally {
            try {
                hessianOutput.close();
            } catch (IOException e) {
                //ignore
            }
        }
        return baos.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        HessianInput hessianInput = new HessianInput(bais);
        Object object;
        try {
            object = hessianInput.readObject();
        } catch (IOException e) {
            throw new HawthornException(e);
        } finally {
            hessianInput.close();
        }
        return (T) object;
    }
}
