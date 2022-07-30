package id.holigo.services.holigofareservice.web.controllers;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.holigo.services.common.model.FareDto;
import id.holigo.services.holigofareservice.domain.Fare;
import id.holigo.services.holigofareservice.repositories.FareRepository;
import id.holigo.services.holigofareservice.services.FareService;
import id.holigo.services.holigofareservice.web.mappers.FareMapper;

@Slf4j
@RestController
public class FareController {

    @Autowired
    private FareService fareService;

    @Autowired
    private FareRepository fareRepository;

    @Autowired
    private FareMapper fareMapper;

    @GetMapping("api/v1/fare/{id}")
    public ResponseEntity<FareDto> get(@PathVariable("id") Long id) {
        Fare fare = fareRepository.getById(id);
        return new ResponseEntity<>(fareMapper.fareToFareDto(fare), HttpStatus.OK);
    }

    @GetMapping("api/v1/fare")
    public ResponseEntity<FareDto> get(@RequestParam("userId") Long userId,
            @RequestParam("productId") Integer productId, @RequestParam("ntaAmount") BigDecimal ntaAmount,
            @RequestParam("nraAmount") BigDecimal nraAmount) {
        log.info("get running...");
        Fare fare = fareService.calculate(userId, productId, ntaAmount, nraAmount);

        return new ResponseEntity<>(fareMapper.fareToFareDto(fare), HttpStatus.OK);
    }
}
