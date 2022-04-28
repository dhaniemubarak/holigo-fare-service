package id.holigo.services.holigofareservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {
    public static final String GET_USER_DATA_BY_PHONE_NUMBER_QUEUE = "get-user-data-by-phone-number-queue";

    public static final String GET_USER_DATA_BY_ID_QUEUE = "get-user-data-by-id-queue";

    public static final String GET_DETAIL_FARE_PRODUCT = "get-detail-fare-product";

    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
