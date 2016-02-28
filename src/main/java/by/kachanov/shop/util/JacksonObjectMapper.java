package by.kachanov.shop.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        registerModule(hibernate5Module);
    }

}
