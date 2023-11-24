/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ExchangeRateEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long exchangeRateEntryId;
    private Long exchangeRateId;
    private String currencyCode;
    private String currencyNameCode;
    private BigDecimal buyRate;
    private BigDecimal middleRate;
    private BigDecimal sellRate;
    private OffsetDateTime timestampOfCreating;

    public ExchangeRateEntry() {}

    public ExchangeRateEntry(ExchangeRateEntry value) {
        this.exchangeRateEntryId = value.exchangeRateEntryId;
        this.exchangeRateId = value.exchangeRateId;
        this.currencyCode = value.currencyCode;
        this.currencyNameCode = value.currencyNameCode;
        this.buyRate = value.buyRate;
        this.middleRate = value.middleRate;
        this.sellRate = value.sellRate;
        this.timestampOfCreating = value.timestampOfCreating;
    }

    public ExchangeRateEntry(
        Long exchangeRateEntryId,
        Long exchangeRateId,
        String currencyCode,
        String currencyNameCode,
        BigDecimal buyRate,
        BigDecimal middleRate,
        BigDecimal sellRate,
        OffsetDateTime timestampOfCreating
    ) {
        this.exchangeRateEntryId = exchangeRateEntryId;
        this.exchangeRateId = exchangeRateId;
        this.currencyCode = currencyCode;
        this.currencyNameCode = currencyNameCode;
        this.buyRate = buyRate;
        this.middleRate = middleRate;
        this.sellRate = sellRate;
        this.timestampOfCreating = timestampOfCreating;
    }

    /**
     * Getter for
     * <code>public.exchange_rate_entry.exchange_rate_entry_id</code>.
     */
    public Long getExchangeRateEntryId() {
        return this.exchangeRateEntryId;
    }

    /**
     * Setter for
     * <code>public.exchange_rate_entry.exchange_rate_entry_id</code>.
     */
    public void setExchangeRateEntryId(Long exchangeRateEntryId) {
        this.exchangeRateEntryId = exchangeRateEntryId;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.exchange_rate_id</code>.
     */
    public Long getExchangeRateId() {
        return this.exchangeRateId;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.exchange_rate_id</code>.
     */
    public void setExchangeRateId(Long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.currency_code</code>.
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.currency_code</code>.
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.currency_name_code</code>.
     */
    public String getCurrencyNameCode() {
        return this.currencyNameCode;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.currency_name_code</code>.
     */
    public void setCurrencyNameCode(String currencyNameCode) {
        this.currencyNameCode = currencyNameCode;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.buy_rate</code>.
     */
    public BigDecimal getBuyRate() {
        return this.buyRate;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.buy_rate</code>.
     */
    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.middle_rate</code>.
     */
    public BigDecimal getMiddleRate() {
        return this.middleRate;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.middle_rate</code>.
     */
    public void setMiddleRate(BigDecimal middleRate) {
        this.middleRate = middleRate;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.sell_rate</code>.
     */
    public BigDecimal getSellRate() {
        return this.sellRate;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.sell_rate</code>.
     */
    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    /**
     * Getter for <code>public.exchange_rate_entry.timestamp_of_creating</code>.
     */
    public OffsetDateTime getTimestampOfCreating() {
        return this.timestampOfCreating;
    }

    /**
     * Setter for <code>public.exchange_rate_entry.timestamp_of_creating</code>.
     */
    public void setTimestampOfCreating(OffsetDateTime timestampOfCreating) {
        this.timestampOfCreating = timestampOfCreating;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ExchangeRateEntry other = (ExchangeRateEntry) obj;
        if (this.exchangeRateEntryId == null) {
            if (other.exchangeRateEntryId != null)
                return false;
        }
        else if (!this.exchangeRateEntryId.equals(other.exchangeRateEntryId))
            return false;
        if (this.exchangeRateId == null) {
            if (other.exchangeRateId != null)
                return false;
        }
        else if (!this.exchangeRateId.equals(other.exchangeRateId))
            return false;
        if (this.currencyCode == null) {
            if (other.currencyCode != null)
                return false;
        }
        else if (!this.currencyCode.equals(other.currencyCode))
            return false;
        if (this.currencyNameCode == null) {
            if (other.currencyNameCode != null)
                return false;
        }
        else if (!this.currencyNameCode.equals(other.currencyNameCode))
            return false;
        if (this.buyRate == null) {
            if (other.buyRate != null)
                return false;
        }
        else if (!this.buyRate.equals(other.buyRate))
            return false;
        if (this.middleRate == null) {
            if (other.middleRate != null)
                return false;
        }
        else if (!this.middleRate.equals(other.middleRate))
            return false;
        if (this.sellRate == null) {
            if (other.sellRate != null)
                return false;
        }
        else if (!this.sellRate.equals(other.sellRate))
            return false;
        if (this.timestampOfCreating == null) {
            if (other.timestampOfCreating != null)
                return false;
        }
        else if (!this.timestampOfCreating.equals(other.timestampOfCreating))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.exchangeRateEntryId == null) ? 0 : this.exchangeRateEntryId.hashCode());
        result = prime * result + ((this.exchangeRateId == null) ? 0 : this.exchangeRateId.hashCode());
        result = prime * result + ((this.currencyCode == null) ? 0 : this.currencyCode.hashCode());
        result = prime * result + ((this.currencyNameCode == null) ? 0 : this.currencyNameCode.hashCode());
        result = prime * result + ((this.buyRate == null) ? 0 : this.buyRate.hashCode());
        result = prime * result + ((this.middleRate == null) ? 0 : this.middleRate.hashCode());
        result = prime * result + ((this.sellRate == null) ? 0 : this.sellRate.hashCode());
        result = prime * result + ((this.timestampOfCreating == null) ? 0 : this.timestampOfCreating.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ExchangeRateEntry (");

        sb.append(exchangeRateEntryId);
        sb.append(", ").append(exchangeRateId);
        sb.append(", ").append(currencyCode);
        sb.append(", ").append(currencyNameCode);
        sb.append(", ").append(buyRate);
        sb.append(", ").append(middleRate);
        sb.append(", ").append(sellRate);
        sb.append(", ").append(timestampOfCreating);

        sb.append(")");
        return sb.toString();
    }
}