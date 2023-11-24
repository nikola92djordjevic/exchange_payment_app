package rs.payment.exchange.dto.respones;

import PlatniPrometDB.tables.pojos.CashRegister;
import PlatniPrometDB.tables.pojos.WorkPlace;


public class CashRegisterResponse {
    public CashRegister cashRegister;
    public WorkPlace workPlace;
    public BalanceResponse balanceResponse;

    public CashRegisterResponse(CashRegister cashRegister, WorkPlace workPlace, BalanceResponse balanceResponse) {
        this.cashRegister = cashRegister;
        this.workPlace = workPlace;
        this.balanceResponse = balanceResponse;
    }
}
