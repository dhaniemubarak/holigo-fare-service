package id.holigo.services.holigofareservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.holigo.services.common.model.UserDto;
import id.holigo.services.holigofareservice.components.Calculate;
import id.holigo.services.holigofareservice.domain.MarginAllocation;
import id.holigo.services.holigofareservice.repositories.MarginAllocationRepository;
import id.holigo.services.holigofareservice.services.user.UserService;

@SpringBootTest
public class FareServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    MarginAllocationRepository marginRepository;

    UserDto user;
    Integer productId = 1;
    BigDecimal nraAmount;
    BigDecimal ntaAmount;
    BigDecimal muAmount;

    MarginAllocation marginAllocation;

    @BeforeEach
    void setUp() throws JsonMappingException, JsonProcessingException, JMSException {
        user = userService.getUserById(5L);
        nraAmount = new BigDecimal(100000);
        ntaAmount = new BigDecimal(1000000);
        Optional<MarginAllocation> fetchMarginAllocation = marginRepository
                .findByUserGroupAndProductId(user.getUserGroup(), productId);
        if (fetchMarginAllocation.isPresent()) {
            marginAllocation = fetchMarginAllocation.get();
        } else {
            marginAllocation = MarginAllocation.builder().userGroup(user.getUserGroup())
                    .productId(productId)
                    .cpPercentage(0.55)
                    .ipPercentage(0.2)
                    .mpPercentage(0.10)
                    .hvPercentage(0.05)
                    .prPercentage(0.05)
                    .hpPercentage(0.00)
                    .hpcPercentage(0.05).build();
        }
    }

    @Test
    void testCalculate() {
        Calculate calculate = new Calculate(user, marginAllocation, nraAmount, nraAmount);

        assertEquals(calculate.getNraAmount().setScale(2), nraAmount.setScale(2));
        assertEquals(BigDecimal.valueOf(55000.00).setScale(2),
                calculate.getCpAmount());
        assertEquals(new BigDecimal(0.00).setScale(2),
                calculate.getIpcAmount());
        assertEquals(new BigDecimal(5000.00).setScale(2), calculate.getHvAmount());
    }
}
