package id.holigo.services.holigofareservice.listeners;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import id.holigo.services.common.model.FareDetailDto;
import id.holigo.services.common.model.FareDto;
import id.holigo.services.holigofareservice.config.JmsConfig;
import id.holigo.services.holigofareservice.domain.Fare;
import id.holigo.services.holigofareservice.services.FareService;
import id.holigo.services.holigofareservice.web.mappers.FareMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FareDetailListener {

    @Autowired
    private final JmsTemplate jmsTemplate;

    @Autowired
    private final FareService fareService;

    @Autowired
    private FareMapper fareMapper;

    @JmsListener(destination = JmsConfig.GET_DETAIL_FARE_PRODUCT)
    public void getDetailFareProduct(@Payload FareDetailDto fareDetailDto, Message message) throws JMSException {
        Fare fare = fareService.calculate(fareDetailDto.getUserId(), fareDetailDto.getProductId(),
                fareDetailDto.getNraAmount());
        FareDto fareDto = fareMapper.fareToFareDto(fare);
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), fareDto);
    }
}
