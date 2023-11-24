package rs.payment.exchange.dto.record;

import java.math.BigDecimal;

public class BalanceRecordResult {
    public Long cashRegisterId;
    public Integer entrySide;
    public String currencyCode;
    public BigDecimal balance;
}
