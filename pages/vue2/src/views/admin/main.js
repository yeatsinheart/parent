import Vue from 'vue'
import router from '@/views/admin/router'
import store from '@/store'
// 导入配置好的国际化语言包

import $ from 'jquery'
import i18n from '@/language'
Vue.prototype.$ = $
const viewer = () => import(`@/views/admin/viewer.vue`)
new Vue({
    i18n, router, store, render: h => h(viewer)
}).$mount('#content')
