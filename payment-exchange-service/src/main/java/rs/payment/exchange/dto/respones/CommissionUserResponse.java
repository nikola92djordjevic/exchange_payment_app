package rs.payment.exchange.dto.respones;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CommissionUserResponse {
    public BigDecimal buyCommission;
    public BigDecimal sellCommission;
    public BigDecimal totalCommission;
    public Long userId;
    public LocalDate dateOfCommission;

    public CommissionUserResponse() {
        this.buyCommission = BigDecimal.ZERO;
        this.sellCommission = BigDecimal.ZERO;
        this.totalCommission = BigDecimal.ZERO;
    }
}
