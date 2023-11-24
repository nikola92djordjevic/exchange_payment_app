const locales = {
  sr: 'sr-Latn-RS',
  en: 'en-US'
}
export default {
  formatLocalizedAmount (value, decimals = 2, locale = 'sr') {
    const formatLocale = locales[locale] || locale
    if (!value) return ''
    if (typeof value === 'number') {
      return value.toLocaleString(formatLocale, { minimumFractionDigits: decimals, maximumFractionDigits: decimals })
    } else if (typeof value === 'string') {
      const newVal = +value
      if (!isNaN(newVal)) {
        return newVal.toLocaleString(formatLocale, { minimumFractionDigits: decimals, maximumFractionDigits: decimals })
      }
      return ''
    }
    return ''
  },
  formatCurrency (value) {
    /**
     * Rounds to two decimal places with rounding to higher number based on decimals
     * For example 430.3265 will become 430.33
     */
    if (!value) return ''
    if (typeof value === 'number') {
      return value.toFixed(2).toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
    } else if (typeof value === 'string') {
      const newVal = +value
      if (!isNaN(newVal)) {
        return newVal.toFixed(2).toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
      }
    } else {
      return ''
    }
  },
  formatAmount (value) {
    /**
     * Same as above, but it formats in Serbian Dinar format
     */
    if (!value) return ''
    if (typeof value === 'number') {
      return value.toFixed(2).toString().replace(/\./g, ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
    } else if (typeof value === 'string') {
      const newVal = +value
      if (!isNaN(newVal)) {
        return newVal.toFixed(2).toString().replace(/\./g, ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
      }
    } else {
      return ''
    }
  },
  formatAmountWithoutDecimals (value) {
    /**
     * Same as above, but it formats in Serbian Dinar format without decimals
     */
    if (typeof value === 'number') {
      return value.toFixed().toString().replace(/\./g, ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
    } else if (typeof value === 'string') {
      const newVal = +value
      if (!isNaN(newVal)) {
        return newVal.toFixed().toString().replace(/\./g, ',').replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.')
      }
    } else {
      return ''
    }
  }
}
