import Vue from 'vue'
import VueRouter from 'vue-router'
//if (!window.VueRouter)
Vue.use(VueRouter)

import {url, getRoute} from '@/views/admin/router/url/index'

let routes = []
for (const key in url) {
    //frame 切换
    let frame = localStorage.getItem('choosed-frame') || localStorage.getItem('application-default-frame') || process.env.VUE_APP_DEFAULT_FRAME
    let urlConfig = getRoute(key, frame)
    if (urlConfig) {
        routes.push(urlConfig)
    }
}
console.log("所有路由", routes)
const router = new VueRouter({
    base:'admin',
    mode: 'history', /* base: process.env.BASE_URL,*/
    routes
})
router.beforeEach((to, from, next) => {
    console.log(to)
    let page = "admin.html"
    let path = to.path.replace(page, "")
    // 登陆拦截
    if (to.meta && to.meta.needLogin && to.meta.needLogin === true) {
        console.log(to)
        console.log("需要登陆")
        next({path: '/login'})
    }
    if (to.path.indexOf(page)!=-1) {
        console.log("包含入口后缀")
        next({path: to.path.replace(page, "")})
    }
    console.log("没有入口后缀"+to.path+path.indexOf(page))
    next()
})
export default router
