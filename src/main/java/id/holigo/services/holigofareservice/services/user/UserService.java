package id.holigo.services.holigofareservice.services.user;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import id.holigo.services.common.model.UserDto;

public interface UserService {

    Boolean isExistsByPhoneNumber(String phoneNumber)
            throws JMSException, JsonMappingException, JsonProcessingException;

    UserDto getUserByPhoneNumber(String phoneNumber) throws JMSException, JsonMappingException, JsonProcessingException;

    UserDto getUserById(Long userId)  throws JsonMappingException, JsonProcessingException, JMSException;
}
