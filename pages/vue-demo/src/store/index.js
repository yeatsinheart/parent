import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        choosedTheme: localStorage.getItem('choosed-theme') || localStorage.getItem('application-default-theme') || process.env.VUE_APP_DEFAULT_THEME,
        choosedLanguage: localStorage.getItem('choosed-language') || localStorage.getItem('application-default-language') || process.env.VUE_APP_LANGUAGE,
        user: null,
        tagsList: [], //打开的标签页个数,
        tagActive: [], //打开的标签页个数,
        isCollapse: false, //侧边导航是否折叠
    }, mutations: {
        changeTheme: function (state, theme) {
            state.choosedTheme = theme
            localStorage.setItem('choosed-theme', theme)
        }, changeLanguage: function (state, language) {
            state.choosedLanguage = language
        }, toogleSide: function (state) {
            state.isCollapse = !state.isCollapse
        }, taglist: function (state, tag) {
            console.log("新增标签", tag)
            let isIn = state.tagsList.some(item => {
                //判断标签是否存在
                return item.title === tag.title;
            });
            //不存在
            if (!isIn) {
                // 判断当前的标签个数
                if (state.tagsList.length >= 10) {
                    // messages("warning", "当标签大于10个，请关闭后再打开");
                } else {
                    state.tagsList.push({
                        title: tag.title,
                        url: tag.url
                    });

                }
            }
        }
    }, actions: {}, modules: {}
})
