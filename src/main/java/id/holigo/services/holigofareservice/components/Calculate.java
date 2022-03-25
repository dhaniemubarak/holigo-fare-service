package id.holigo.services.holigofareservice.components;

import java.math.BigDecimal;
import java.math.RoundingMode;

import id.holigo.services.common.model.UserDto;
import id.holigo.services.holigofareservice.domain.MarginAllocation;
import lombok.Getter;
import lombok.Setter;

// @Component
@Getter
@Setter
public class Calculate {

    public Calculate(UserDto user, MarginAllocation marginAllocation, BigDecimal nraAmount) {
        setUser(user);
        setCpPercentage(marginAllocation.getCpPercentage());
        setIpPercentage(marginAllocation.getIpPercentage());
        setMpPercentage(marginAllocation.getMpPercentage());
        setHpPercentage(marginAllocation.getHpPercentage());
        setHvPercentage(marginAllocation.getHvPercentage());
        setPrPercentage(marginAllocation.getPrPercentage());
        setHpcPercentage(marginAllocation.getHpcPercentage());
        setProductId(productId);
        setNraAmount(nraAmount);
        setPcAmount(nraAmount);
        setMpAmount(nraAmount);
        setPiAmount(nraAmount);
        setHvAmount(nraAmount);
        setHpAmount(nraAmount);
        setPrAmount(nraAmount);
    }

    private Double cpPercentage;
    private Double mpPercentage;
    private Double ipPercentage;
    private Double hpPercentage;
    private Double hvPercentage;
    private Double prPercentage;
    private Double hpcPercentage;

    private UserDto user;
    private Integer productId;
    private BigDecimal nraAmount;
    private BigDecimal cpAmount;
    private BigDecimal mpAmount;
    private BigDecimal ipAmount;
    private BigDecimal hpAmount;
    private BigDecimal hvAmount;
    private BigDecimal prAmount;
    private BigDecimal ipcAmount;
    private BigDecimal hpcAmount;
    private BigDecimal prcAmount;

    public void setPcAmount(BigDecimal nraAmount) {
        this.cpAmount = nraAmount.multiply(new BigDecimal(cpPercentage));
        this.cpAmount = this.cpAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setMpAmount(BigDecimal nraAmount) {
        this.mpAmount = nraAmount.multiply(new BigDecimal(mpPercentage));
        this.mpAmount = this.mpAmount.setScale(2, RoundingMode.HALF_DOWN);
    }

    public void setPiAmount(BigDecimal nraAmount) {
        BigDecimal ip = nraAmount.multiply(new BigDecimal(ipPercentage));
        if (getUser().getOfficialId() == null) {
            setIpcAmount(ip);
            this.ipAmount = new BigDecimal(0);
        } else {
            setPcAmount(new BigDecimal(0));
            this.ipAmount = ip;
        }
        this.ipAmount = this.ipAmount.setScale(2, RoundingMode.HALF_DOWN);
    }

    public void setHvAmount(BigDecimal nraAmount) {
        this.hvAmount = nraAmount.multiply(new BigDecimal(hvPercentage));
    }

    public void setHpAmount(BigDecimal nraAmount) {
        BigDecimal hpBasic = nraAmount.multiply(new BigDecimal(hpPercentage));
        BigDecimal hpUser = nraAmount.multiply(new BigDecimal(getHpcPercentage())).setScale(2,
                RoundingMode.HALF_DOWN);
        this.hpAmount = hpUser;
        setHpcAmount(hpBasic.subtract(hpUser));

    }

    public void setPrAmount(BigDecimal nraAmount) {
        BigDecimal pr = nraAmount.multiply(new BigDecimal(prPercentage));
        if (getUser().getParentId() == null) {
            setPrcAmount(pr);
            this.prAmount = new BigDecimal(0);
        } else {
            setPrcAmount(new BigDecimal(0));
            this.prAmount = pr;
        }
    }

    public void setIpcAmount(BigDecimal ipcAmount) {
        this.ipcAmount = ipcAmount;
    }

    public void setHpcAmount(BigDecimal hpcAmount) {
        this.hpcAmount = hpcAmount;
    }

    public void setPrcAmount(BigDecimal prcAmount) {
        this.prcAmount = prcAmount;
    }
}
