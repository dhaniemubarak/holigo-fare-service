package id.holigo.services.holigofareservice.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import id.holigo.services.common.model.UserGroupEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer productId;

    @Convert(converter = UserGroupEnumConverter.class)
    private UserGroupEnum userGroup;

    @Column(columnDefinition = "decimal(12,2) default 0")
    private BigDecimal fareAmount;

    @Column(columnDefinition = "decimal(12,2) default 0")
    private BigDecimal ntaAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal nraAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal cpAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal mpAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal ipAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal hpAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal hvAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal prAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal ipcAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal hpcAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal prcAmount;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private BigDecimal lossAmount;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
