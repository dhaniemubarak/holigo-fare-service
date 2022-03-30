package id.holigo.services.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FareDto implements Serializable {

    static final long serialVersionUID = 660L;

    private Long id;

    private UserGroupEnum userGroup;

    private Integer productId;

    private BigDecimal nraAmount;

    @Builder.Default
    private BigDecimal fareAmount = new BigDecimal("0.00");

    private BigDecimal cpAmount;

    private BigDecimal mpAmount;

    private BigDecimal ipAmount;

    private BigDecimal hpAmount;

    private BigDecimal hvAmount;

    private BigDecimal prAmount;

    private BigDecimal ipcAmount;

    private BigDecimal hvcAmount;

    private BigDecimal prcAmount;

    private BigDecimal mucAmount;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
