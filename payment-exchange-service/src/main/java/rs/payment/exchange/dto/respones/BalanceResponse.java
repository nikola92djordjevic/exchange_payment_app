package rs.payment.exchange.dto.respones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceResponse {
    public static class BalanceRecord {
        public String currencyCode;
        public BigDecimal balance;
        public BalanceRecord() {
            this.balance = BigDecimal.ZERO;
        }
        public BalanceRecord(String currencyCode, BigDecimal balance) {
            this.currencyCode = currencyCode;
            this.balance = balance;
        }
    }

    public Long cashRegisterId;
    public String cashRegisterName;
    public List<BalanceRecord> balanceList;

    public BalanceResponse(){
        balanceList = new ArrayList<>();
    }

}
