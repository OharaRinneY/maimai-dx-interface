package moe.yiheng.servicebase.feign;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import moe.yiheng.servicebase.Payload;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author rinne
 * @Date 2022/2/28
 */
public class FeignPayloadDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (response.body() == null) {
            throw new DecodeException(response.status(), "Empty Feign response", response.request());
        }
        String body = IOUtils.toString(response.body().asReader(StandardCharsets.UTF_8));
        // body to json
        ObjectMapper objectMapper = new ObjectMapper();

        JavaType innerType = TypeFactory.defaultInstance().constructType(type);
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(Payload.class, innerType);

        Payload payload = (Payload) objectMapper.readValue(body, javaType);

        if (!payload.getSuccess()) {
            throw new MyException(payload.getCode(), payload.getMessage());
        }
        if (payload.getData() == null) {
            throw new MyException(500, "调用服务出错");
        }
        return payload.getData();
    }
}
