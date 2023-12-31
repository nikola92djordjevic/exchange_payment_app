/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    private String currencyCode;
    private String currencyNameCode;
    private String fullName;
    private Integer quantity;
    private String country;
    private String signLogo;

    public Currency() {}

    public Currency(Currency value) {
        this.currencyCode = value.currencyCode;
        this.currencyNameCode = value.currencyNameCode;
        this.fullName = value.fullName;
        this.quantity = value.quantity;
        this.country = value.country;
        this.signLogo = value.signLogo;
    }

    public Currency(
        String currencyCode,
        String currencyNameCode,
        String fullName,
        Integer quantity,
        String country,
        String signLogo
    ) {
        this.currencyCode = currencyCode;
        this.currencyNameCode = currencyNameCode;
        this.fullName = fullName;
        this.quantity = quantity;
        this.country = country;
        this.signLogo = signLogo;
    }

    /**
     * Getter for <code>public.currency.currency_code</code>.
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * Setter for <code>public.currency.currency_code</code>.
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Getter for <code>public.currency.currency_name_code</code>.
     */
    public String getCurrencyNameCode() {
        return this.currencyNameCode;
    }

    /**
     * Setter for <code>public.currency.currency_name_code</code>.
     */
    public void setCurrencyNameCode(String currencyNameCode) {
        this.currencyNameCode = currencyNameCode;
    }

    /**
     * Getter for <code>public.currency.full_name</code>.
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Setter for <code>public.currency.full_name</code>.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter for <code>public.currency.quantity</code>.
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Setter for <code>public.currency.quantity</code>.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for <code>public.currency.country</code>.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Setter for <code>public.currency.country</code>.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter for <code>public.currency.sign_logo</code>.
     */
    public String getSignLogo() {
        return this.signLogo;
    }

    /**
     * Setter for <code>public.currency.sign_logo</code>.
     */
    public void setSignLogo(String signLogo) {
        this.signLogo = signLogo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Currency other = (Currency) obj;
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
        if (this.fullName == null) {
            if (other.fullName != null)
                return false;
        }
        else if (!this.fullName.equals(other.fullName))
            return false;
        if (this.quantity == null) {
            if (other.quantity != null)
                return false;
        }
        else if (!this.quantity.equals(other.quantity))
            return false;
        if (this.country == null) {
            if (other.country != null)
                return false;
        }
        else if (!this.country.equals(other.country))
            return false;
        if (this.signLogo == null) {
            if (other.signLogo != null)
                return false;
        }
        else if (!this.signLogo.equals(other.signLogo))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.currencyCode == null) ? 0 : this.currencyCode.hashCode());
        result = prime * result + ((this.currencyNameCode == null) ? 0 : this.currencyNameCode.hashCode());
        result = prime * result + ((this.fullName == null) ? 0 : this.fullName.hashCode());
        result = prime * result + ((this.quantity == null) ? 0 : this.quantity.hashCode());
        result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());
        result = prime * result + ((this.signLogo == null) ? 0 : this.signLogo.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Currency (");

        sb.append(currencyCode);
        sb.append(", ").append(currencyNameCode);
        sb.append(", ").append(fullName);
        sb.append(", ").append(quantity);
        sb.append(", ").append(country);
        sb.append(", ").append(signLogo);

        sb.append(")");
        return sb.toString();
    }
}
