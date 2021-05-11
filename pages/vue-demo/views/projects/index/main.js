import Vue from 'vue'
import router from './router'
import store from '@/store'
// 导入配置好的国际化语言包
import $ from 'jquery'
import i18n from './language'

Vue.prototype.$ = $
Vue.prototype.project = 'index'
let header
let footer
console.log(process.env.VUE_APP_HEADER, process.env.VUE_APP_FOOTER)
const Header = () => import(`./` + process.env.VUE_APP_HEADER)
const Footer = () => import(`./` + process.env.VUE_APP_FOOTER)
/* 头部尾部控制 */
/*导航放到外部，不至于出现无法滚动的情况*/
router.beforeEach((to, from, next) => {

    if (header == null) {
        Header().then(obj => {
            let h = Vue.extend(obj.default)
            header = new h({i18n, router, store, propsData: {header: to.meta.head}}).$mount('#header')
        })
    }
    if (footer == null) {
        Footer().then(obj => {
            let h = Vue.extend(obj.default)
            footer = new h({i18n, router, store, propsData: {header: to.meta.foot}}).$mount('#footer')
        })
    }
    next()
})
const viewer = () => import(`./viewer.vue`)
new Vue({
    i18n, router, store, render: h => h(viewer)
}).$mount('#content')
