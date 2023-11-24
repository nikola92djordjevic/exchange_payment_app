package rs.payment.exchange.dto.requests;

import PlatniPrometDB.tables.pojos.ExchangeRate;
import PlatniPrometDB.tables.pojos.ExchangeRateEntry;

import java.util.List;

public class ExchangeRateCreateRequest {
    public ExchangeRate exchangeRate;

    public List<ExchangeRateEntry> exchangeRateEntryList;
}
