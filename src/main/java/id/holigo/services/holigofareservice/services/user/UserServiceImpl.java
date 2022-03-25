package id.holigo.services.holigofareservice.services.user;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import id.holigo.services.common.model.UserDto;
import id.holigo.services.holigofareservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber)
            throws JMSException, JsonMappingException, JsonProcessingException {
        UserDto userDto = UserDto.builder().phoneNumber(phoneNumber).build();
        Message received = jmsTemplate.sendAndReceive(JmsConfig.GET_USER_DATA_BY_PHONE_NUMBER_QUEUE,
                new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        Message userDataMessage = null;
                        try {
                            userDataMessage = session.createTextMessage(objectMapper.writeValueAsString(userDto));
                            userDataMessage.setStringProperty("_type", "id.holigo.services.common.model.UserDto");
                        } catch (JsonProcessingException e) {
                            throw new JMSException(e.getMessage());
                        }
                        return userDataMessage;
                    }
                });
        UserDto result = objectMapper.readValue(received.getBody(String.class), UserDto.class);
        return result;
    }

    @Override
    public Boolean isExistsByPhoneNumber(String phoneNumber)
            throws JMSException, JsonMappingException, JsonProcessingException {
        UserDto userDto = this.getUserByPhoneNumber(phoneNumber);
        if (userDto.getId() != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserDto getUserById(Long userId) throws JsonMappingException, JsonProcessingException, JMSException {
        UserDto userDto = UserDto.builder().id(userId).build();
        Message received = jmsTemplate.sendAndReceive(JmsConfig.GET_USER_DATA_BY_ID_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message userDataMessage = null;
                try {
                    userDataMessage = session.createTextMessage(objectMapper.writeValueAsString(userDto));
                    userDataMessage.setStringProperty("_type", "id.holigo.services.common.model.UserDto");
                } catch (JsonProcessingException e) {
                    throw new JMSException(e.getMessage());
                }
                return userDataMessage;
            }
        });
        UserDto result = objectMapper.readValue(received.getBody(String.class), UserDto.class);
        return result;
    }

}
