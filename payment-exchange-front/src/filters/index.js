import dateFormatting from './dateFormatting'
import currencyFormatting from './currencyFormatting'
import stringFormatting from './stringFormatting'

/**
 * Using spread operator so everything would be exported under one object
 */
export default {
  ...dateFormatting,
  ...currencyFormatting,
  ...stringFormatting
}
