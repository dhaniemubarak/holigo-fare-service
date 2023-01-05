package id.holigo.services.holigofareservice.services.user;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import id.holigo.services.common.model.UserDto;
import id.holigo.services.holigofareservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;

import java.beans.PropertyEditorSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber)
            throws JMSException, JsonProcessingException {
        UserDto userDto = UserDto.builder().phoneNumber(phoneNumber).build();
        Message received = jmsTemplate.sendAndReceive(JmsConfig.GET_USER_DATA_BY_PHONE_NUMBER_QUEUE,
                session -> {
                    Message userDataMessage;
                    try {
                        userDataMessage = session.createTextMessage(objectMapper.writeValueAsString(userDto));
                        userDataMessage.setStringProperty("_type", "id.holigo.services.common.model.UserDto");
                    } catch (JsonProcessingException e) {
                        throw new JMSException(e.getMessage());
                    }
                    return userDataMessage;
                });
        assert received != null;
        return objectMapper.readValue(received.getBody(String.class), UserDto.class);
    }

    @Override
    public Boolean isExistsByPhoneNumber(String phoneNumber)
            throws JMSException, JsonProcessingException {
        UserDto userDto = this.getUserByPhoneNumber(phoneNumber);
        return userDto.getId() != null;
    }

    @Override
    public UserDto getUserById(Long userId) throws JsonProcessingException, JMSException {
        UserDto userDto = UserDto.builder().id(userId).build();
        Message received = jmsTemplate.sendAndReceive(JmsConfig.GET_USER_DATA_BY_ID_QUEUE, session -> {
            Message userDataMessage;
            try {
                userDataMessage = session.createTextMessage(objectMapper.writeValueAsString(userDto));
                userDataMessage.setStringProperty("_type", "id.holigo.services.common.model.UserDto");
            } catch (JsonProcessingException e) {
                throw new JMSException(e.getMessage());
            }
            return userDataMessage;
        });

        assert received != null;
        return objectMapper.readValue(received.getBody(String.class), UserDto.class);
    }

}
