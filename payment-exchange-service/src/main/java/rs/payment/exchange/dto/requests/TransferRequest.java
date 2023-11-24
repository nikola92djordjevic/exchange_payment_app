package rs.payment.exchange.dto.requests;

import PlatniPrometDB.tables.pojos.BalanceEntry;

import java.math.BigDecimal;
import java.util.List;

public class TransferRequest {
    public Long cashRegisterId;
    public Long treasuryId;
    public BigDecimal amount;
    public String currencyCode;

    public List<BalanceEntry> balanceEntryList;


}
