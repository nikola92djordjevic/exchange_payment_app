package rs.payment.exchange.dto.requests;

import PlatniPrometDB.tables.pojos.BalanceEntry;
import PlatniPrometDB.tables.pojos.ExchangeCashDenomination;
import PlatniPrometDB.tables.pojos.ExchangeJob;

import java.util.List;

public class ExchangeMoneyRequest {

    public ExchangeJob exchangeJob;
    public ExchangeCashDenomination exchangeCashDenomination;
    public List<BalanceEntry> balanceEntryList;
}
