package id.holigo.services.holigofareservice.services.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.holigo.services.common.model.UserDto;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    String phoneNumber;

    @BeforeEach
    void setUp() {
        phoneNumber = "6285718187373";
    }

    @Test
    void testGetUserByPhoneNumber() throws JsonMappingException, JsonProcessingException, JMSException {
        UserDto userDto = userService.getUserByPhoneNumber(phoneNumber);
        assertNotNull(userDto.getType());
    }
}
