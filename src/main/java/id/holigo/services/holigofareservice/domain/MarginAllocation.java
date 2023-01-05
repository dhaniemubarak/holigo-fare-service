package id.holigo.services.holigofareservice.domain;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MarginAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Convert(converter = UserGroupEnumConverter.class)
    private UserGroupEnum userGroup;

    private Integer productId;

    private Double cpPercentage;

    private Double ipPercentage;

    private Double mpPercentage;

    private Double hpPercentage;

    private Double hvPercentage;

    private Double prPercentage;

    private Double hpcPercentage;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
