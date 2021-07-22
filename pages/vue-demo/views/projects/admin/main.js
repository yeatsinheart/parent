import Vue from 'vue'
import router from './router'
import store from '@/store'
// 导入配置好的国际化语言包
import $ from 'jquery'
//import i18n from './language'

Vue.prototype.$ = $
Vue.prototype.project = 'admin'

router.beforeEach((to, from, next) => {
    next()
})
const viewer = () => import(`./viewer.vue`)
new Vue({
    router, store, render: h => h(viewer)
}).$mount('#content')
//new Vue({
//     i18n, router, store, render: h => h(viewer)
// }).$mount('#content')
