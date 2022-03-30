package id.holigo.services.common.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FareDetailDto {

    private Long userId;
    
    private Integer productId;

    private BigDecimal nraAmount;
}
