package id.holigo.services.holigofareservice.web.mappers;

import org.mapstruct.Mapper;

import id.holigo.services.common.model.FareDto;
import id.holigo.services.holigofareservice.domain.Fare;

@Mapper
public interface FareMapper {
    public Fare fareDtoToFare(FareDto fareDto);

    public FareDto fareToFareDto(Fare fare);
}
