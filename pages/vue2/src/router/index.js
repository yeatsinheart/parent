import Vue from 'vue'
import VueRouter from 'vue-router'
import {getRoute, url} from '@/url/index'
//if (!window.VueRouter)
Vue.use(VueRouter)

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
    mode: 'history', /* base: process.env.BASE_URL,*/
    routes
})
router.beforeEach((to, from, next) => {
    // 登陆拦截
    if (to.meta && to.meta.needLogin && to.meta.needLogin === true) {
        console.log(to)
        console.log("需要登陆")
        next({path: '/login'})
    }
    next()
})
export default router
