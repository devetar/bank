package api.model;

import java.math.BigDecimal;

public enum TransferType {
    INTRA_BANK(new BigDecimal(0), BigDecimal.ZERO, 100),
    INTER_BANK(new BigDecimal(5), new BigDecimal(1000), 70);

    private BigDecimal commision;
    private BigDecimal limit;
    private int successRate;

    TransferType(BigDecimal commision, BigDecimal limit, int successRate) {
        this.commision = commision;
        this.limit = limit;
        this.successRate = successRate;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public int getSuccessRate() {
        return successRate;
    }
}
