/* pc ios android */
export function sysTemInfo() {
  let us = navigator.userAgent.toLowerCase()
  try {
    if ((us.indexOf('android') > -1 || us.indexOf('linux') > -1) || navigator.platform.toLowerCase().indexOf('linux') != -1) {
      return 'android'
    } else if (us.indexOf('iphone') > -1 || us.indexOf('ipad') > -1) {
      return 'ios'
    }
    return 'pc'
  } catch (e) {
    return 'pc'
  }
}
