export default {
  zeroLead (value, num) {
    return value.padStart(num, '0')
  },
  formatAccount (accountNumber) {
    return accountNumber.substring(0, 3) + '-' + accountNumber.substring(3, 14) + '-' + accountNumber.substring(14, 16)
  }
}
