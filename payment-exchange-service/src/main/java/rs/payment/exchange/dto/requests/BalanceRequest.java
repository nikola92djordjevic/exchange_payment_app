package rs.payment.exchange.dto.requests;

public class BalanceRequest {
    public Long cashRegisterId;

    public BalanceRequest() {
    }

    public BalanceRequest(Long cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }
}
