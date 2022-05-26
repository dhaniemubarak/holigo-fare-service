package id.holigo.services.holigofareservice.components;

import java.math.BigDecimal;
import java.math.RoundingMode;

import id.holigo.services.common.model.UserDto;
import id.holigo.services.holigofareservice.domain.MarginAllocation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calculate {

    public Calculate(UserDto user, MarginAllocation marginAllocation, BigDecimal ntaAmount, BigDecimal nraAmount) {
        setUser(user);
        setCpPercentage(marginAllocation.getCpPercentage());
        setIpPercentage(marginAllocation.getIpPercentage());
        setMpPercentage(marginAllocation.getMpPercentage());
        setHpPercentage(marginAllocation.getHpPercentage());
        setHvPercentage(marginAllocation.getHvPercentage());
        setPrPercentage(marginAllocation.getPrPercentage());
        setHpcPercentage(marginAllocation.getHpcPercentage());
        setNtaAmount(ntaAmount.setScale(0, RoundingMode.DOWN));
        setProductId(productId);
        setNraAmount(nraAmount.setScale(0, RoundingMode.DOWN));
        setCpAmount(nraAmount);
        setMpAmount(nraAmount);
        setIpAmount(nraAmount);
        setHvAmount(nraAmount);
        setHpAmount(nraAmount);
        setPrAmount(nraAmount);
        setHpcAmount(nraAmount);
        setFareAmount(nraAmount);
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
    private BigDecimal fareAmount;
    private BigDecimal ntaAmount;
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
    private BigDecimal lossAmount;

    public void setCpAmount(BigDecimal nraAmount) {
        this.cpAmount = nraAmount.multiply(BigDecimal.valueOf(cpPercentage)).setScale(0, RoundingMode.DOWN);
        this.cpAmount = this.cpAmount.setScale(2, RoundingMode.DOWN);

    }

    public void setMpAmount(BigDecimal nraAmount) {
        this.mpAmount = nraAmount.multiply(BigDecimal.valueOf(mpPercentage)).setScale(0, RoundingMode.DOWN);
        this.mpAmount = this.mpAmount.setScale(2, RoundingMode.DOWN);
    }

    public void setIpAmount(BigDecimal nraAmount) {
        BigDecimal ip = nraAmount.multiply(BigDecimal.valueOf(ipPercentage)).setScale(0, RoundingMode.DOWN);
        if (getUser().getOfficialId() == null) {
            setIpcAmount(ip);
            this.ipAmount = BigDecimal.valueOf(0.00);
        } else {
            setIpcAmount(BigDecimal.valueOf(0.00));
            this.ipAmount = ip;
        }
        this.ipAmount = this.ipAmount.setScale(2, RoundingMode.DOWN);
    }

    public void setHvAmount(BigDecimal nraAmount) {
        BigDecimal hvAmount = nraAmount.multiply(BigDecimal.valueOf(hvPercentage)).setScale(0, RoundingMode.DOWN);
        this.hvAmount = hvAmount.setScale(2, RoundingMode.DOWN);
    }

    public void setHpAmount(BigDecimal nraAmount) {
        BigDecimal hpAmount = nraAmount.multiply(new BigDecimal(hpPercentage)).setScale(0, RoundingMode.DOWN);
        this.hpAmount = hpAmount.setScale(2, RoundingMode.DOWN);

    }

    public void setPrAmount(BigDecimal nraAmount) {
        BigDecimal pr = nraAmount.multiply(BigDecimal.valueOf(prPercentage)).setScale(0, RoundingMode.DOWN);
        if (getUser().getParent() == null) {
            setPrcAmount(pr);
            this.prAmount = BigDecimal.valueOf(0.00);
        } else {
            setPrcAmount(BigDecimal.valueOf(0.00));
            this.prAmount = pr.setScale(2, RoundingMode.DOWN);
        }
    }

    public void setIpcAmount(BigDecimal ipcAmount) {
        this.ipcAmount = ipcAmount.setScale(2, RoundingMode.DOWN);
    }

    public void setHpcAmount(BigDecimal nraAmount) {
        BigDecimal hpcAmount = nraAmount.multiply(BigDecimal.valueOf(hpcPercentage)).setScale(0, RoundingMode.UP);
        this.hpcAmount = hpcAmount.setScale(2, RoundingMode.UP);
    }

    public void setPrcAmount(BigDecimal prcAmount) {
        this.prcAmount = prcAmount.setScale(2, RoundingMode.DOWN);
    }

    public void setFareAmount(BigDecimal nraAmount) {
        BigDecimal margin = getCpAmount().add(getHpAmount()).add(getHvAmount()).add(getIpcAmount()).add(getPrcAmount())
                .add(getIpAmount()).add(getMpAmount()).add(getPrAmount()).add(getHpcAmount());
        if (nraAmount.compareTo(margin) > 0) {
            setLossAmount(nraAmount.subtract(margin).setScale(0, RoundingMode.DOWN));
        } else {
            setLossAmount(BigDecimal.valueOf(0.00));
        }
        BigDecimal fareAmount = getNtaAmount().add(margin).setScale(0, RoundingMode.UP);
        this.fareAmount = fareAmount.setScale(2, RoundingMode.UP);
    }

    public void setLossAmount(BigDecimal lossAmount) {
        this.lossAmount = lossAmount.setScale(2, RoundingMode.DOWN);
    }
}
