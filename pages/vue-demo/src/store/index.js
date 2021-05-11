import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        choosedTheme: localStorage.getItem('choosed-theme') || localStorage.getItem('application-default-theme') || process.env.VUE_APP_DEFAULT_THEME,
        choosedLanguage: localStorage.getItem('choosed-language') || localStorage.getItem('application-default-language') || process.env.VUE_APP_LANGUAGE,
        user: null
    }, mutations: {
        changeTheme: function (state, theme) {
            state.choosedTheme = theme
            localStorage.setItem('choosed-theme', theme)
        }, changeLanguage: function (state, language) {
            state.choosedLanguage = language
        }
    }, actions: {}, modules: {}
})
