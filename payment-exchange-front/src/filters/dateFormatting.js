import isValid from 'date-fns/isValid'
import parseISO from 'date-fns/parseISO'
import dateFormat from 'date-fns/format'
import distanceInWordsBetweenTwoDates from 'date-fns/formatDistance'

export default {
  shortDateParser(value){
    const val = Object.create(value);
    if (!val) return ''
    if (typeof val === 'object') {
      return `${val.shift()}-${val.shift()}-${val.shift()}`
    }
  },
  dateTimeParser(value){
    const val = Object.create(value);
    if (!val) return ''
    if (typeof val === 'object') {
      if(val.length === 3){
        return `${val.shift()}-${val.shift()}-${val.shift()}`
      }else {
        return `${val.shift()}-${val.shift()}-${val.shift()} ${val.shift()}:${val.shift()}:${val.shift()}`
      }
    }
  },
  shortDate: (value) => {
    if (!value) return ''
    if (typeof value === 'object') {
      return dateFormat(value, 'dd.MM.yyyy.')
    } else if (typeof value === 'string') {
      if (isValid(parseISO(value))) {
        return dateFormat(parseISO(value), 'dd.MM.yyyy.')
      } else {
        return ''
      }
    }
  },
  timeDiffInDays (value) {
    if (!value) return ''
    if (typeof value === 'object') {
      return distanceInWordsBetweenTwoDates(value, new Date())
    } else if (typeof value === 'string') {
      return distanceInWordsBetweenTwoDates(parseISO(value), new Date())
    } else {
      return ''
    }
  },
  timeAndDate (value) {
    if (!value) return ''
    return dateFormat(parseISO(value), 'dd.MM.yyyy H:mm')
  },

}
