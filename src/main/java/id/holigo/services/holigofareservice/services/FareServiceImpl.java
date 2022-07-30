package id.holigo.services.holigofareservice.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.jms.JMSException;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.holigo.services.common.model.UserDto;
import id.holigo.services.holigofareservice.components.Calculate;
import id.holigo.services.holigofareservice.domain.Fare;
import id.holigo.services.holigofareservice.domain.MarginAllocation;
import id.holigo.services.holigofareservice.repositories.FareRepository;
import id.holigo.services.holigofareservice.repositories.MarginAllocationRepository;
import id.holigo.services.holigofareservice.services.user.UserService;

@Service
public class FareServiceImpl implements FareService {

    private FareRepository fareRepository;

    private MarginAllocationRepository marginRepository;

    private UserService userService;

    @Autowired
    public void setFareRepository(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    @Autowired
    public void setMarginRepository(MarginAllocationRepository marginRepository) {
        this.marginRepository = marginRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Fare calculate(Long userId, Integer productId, BigDecimal ntaAmount, BigDecimal nraAmount) {
        Fare fare = null;
        try {

            UserDto user = userService.getUserById(userId);
            Optional<Fare> fetchFare = fareRepository.findByProductIdAndUserGroupAndNtaAmountAndNraAmount(productId,
                    user.getUserGroup(), ntaAmount, nraAmount);
            if (fetchFare.isPresent()) {
                fare = fetchFare.get();
            } else {
                Calculate calculation = new Calculate(user, getMarginAllocation(user, productId), ntaAmount, nraAmount);
                Fare fareCalculation = setFareAllocation(user, productId, nraAmount, calculation);
                fare = fareRepository.save(fareCalculation);
            }
        } catch (JsonProcessingException | JMSException e) {
            e.printStackTrace();
        }
        return fare;
    }

    private MarginAllocation getMarginAllocation(UserDto user, Integer productId) {
        Optional<MarginAllocation> fetchMarginAllocation = marginRepository.findByUserGroupAndProductId(
                user.getUserGroup(), productId);
        MarginAllocation marginAllocation;
        marginAllocation = fetchMarginAllocation.orElseGet(() -> MarginAllocation.builder().userGroup(user.getUserGroup())
                .productId(productId)
                .cpPercentage(0.00)
                .ipPercentage(0.2)
                .mpPercentage(0.00)
                .hvPercentage(0.05)
                .prPercentage(0.05)
                .hpPercentage(0.00)
                .hpcPercentage(0.05).build());
        return marginAllocation;
    }

    private Fare setFareAllocation(UserDto user, Integer productId, BigDecimal nraAmount, Calculate calculation) {
        return Fare.builder()
                .productId(productId)
                .nraAmount(calculation.getNraAmount())
                .userGroup(user.getUserGroup())
                .fareAmount(calculation.getFareAmount())
                .ntaAmount(calculation.getNtaAmount())
                .cpAmount(calculation.getCpAmount())
                .mpAmount(calculation.getMpAmount())
                .ipAmount(calculation.getIpAmount())
                .hpAmount(calculation.getHpAmount())
                .hvAmount(calculation.getHvAmount())
                .prAmount(calculation.getPrAmount())
                .ipcAmount(calculation.getIpcAmount())
                .hpcAmount(calculation.getHpcAmount())
                .prcAmount(calculation.getPrcAmount())
                .lossAmount(calculation.getLossAmount())
                .build();
    }

}
