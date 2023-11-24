package rs.payment.exchange.dto.respones;

import PlatniPrometDB.tables.pojos.ExchangeRate;
import PlatniPrometDB.tables.pojos.ExchangeRateEntry;

import java.util.List;

public class ExchangeRateResponse {
    public ExchangeRate exchangeRate;

    public List<ExchangeRateEntry> exchangeRateEntryList;
}
