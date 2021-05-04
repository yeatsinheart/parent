import Vue from 'vue'
import VueI18n from 'vue-i18n'

Vue.use(VueI18n)
const i18n = new VueI18n({silentTranslationWarn: true})


// 异步加载语言包
async function loadLanguageAsync(lang) {
    let langMsg = i18n.getLocaleMessage(lang)
    // 加载所有views下页面，不加载别的版型客户端的
    let langVueFiles = (require.context(`@/views/`, true, /\.vue$/, 'sync'))
    langVueFiles.keys().forEach(langFile => {
        let parames = langVueFiles(langFile).langMsg
        if (parames && parames[lang]) {
            for (let attr in parames[lang]) {
                langMsg[attr] = parames[lang][attr]
            }
        }
    })
    i18n.setLocaleMessage(lang, langMsg)
    i18n.locale = lang
    localStorage.setItem('choosed-language', lang)
}


// todo 在手动切换语言时调用此方法，页面初始化时需加载一个默认语言，在APP.vue执行一次 ，切换完语言需要重新刷新
// 打包使用默认语言
// 运行使用app默认语言
Vue.prototype.$languageChange = loadLanguageAsync
let initLanguage = localStorage.getItem('choosed-language') || localStorage.getItem('application-default-language') || process.env.VUE_APP_DEFAULT_LANGUAGE
loadLanguageAsync(initLanguage)

export default i18n

