package com.creolophus.lucky.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author ：myq
 * @CreateDate: 2021-01-29 16:04
 * @description：bigDecimal
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.nonNull(bigDecimal)){
            jsonGenerator.writeString(new DecimalFormat("#0.00").format(bigDecimal));
        }
//        if (Objects.isNull(bigDecimal)) {
//            jsonGenerator.writeString("0.00");
//        } else {
//            jsonGenerator.writeString(new DecimalFormat("#,##0.##").format(bigDecimal));
//        }
    }

}
