import Vue from 'vue'
import router from '@/router'
import store from '@/store'
// 导入配置好的国际化语言包

import $ from 'jquery'
import i18n from '@/language'

Vue.prototype.$ = $
let header
let footer
const Header = () => import(`@/views/${process.env.VUE_APP_PROJECT_MODULE}/header/header${process.env.VUE_APP_DEFAULT_FRAME}.vue`)
 const Footer = () => import(`@/views/${process.env.VUE_APP_PROJECT_MODULE}/footer/footer${process.env.VUE_APP_DEFAULT_FRAME}.vue`)
/* 头部尾部控制 */
/*导航放到外部，不至于出现无法滚动的情况*/
router.beforeEach((to, from, next) => {
    if (header == null) {
        Header().then(obj => {
            let h = Vue.extend(obj.default)
            header = new h({ i18n, router, store, propsData: { header: to.meta.head } }).$mount('#header')
        })
    }
    if (footer == null) {
        Footer().then(obj => {
            let h = Vue.extend(obj.default)
            footer = new h({ i18n, router, store, propsData: { header: to.meta.foot } }).$mount('#footer')
        })
    }
    next()
})
const viewer = () => import(`@/views/${process.env.VUE_APP_PROJECT_MODULE}/viewer.vue`)
new Vue({
    i18n, router, store, render: h => h(viewer)
}).$mount('#content')
