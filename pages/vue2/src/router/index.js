import Vue from 'vue'
import VueRouter from 'vue-router'
//if (!window.VueRouter)
Vue.use(VueRouter)

import { url, getRoute } from '@/url/index'
let routes = []
for (const key in url) {
    //frame 切换
    let frame = localStorage.getItem('choosed-frame') || localStorage.getItem('application-default-frame') || process.env.VUE_APP_DEFAULT_FRAME
    let filePath = getRoute(key, frame)
    let metaInfo = {}
    if (filePath) {

        console.log(filePath)
        routes.push({
            path: key, 
            component: () => import(`@/views/modules/${process.env.VUE_APP_PROJECT_MODULE}/${filePath}`),
           // component: () => import('@/views/modules/'+process.env.VUE_APP_PROJECT_MODULE+'/'+ ),
            meta: metaInfo
        })
    }
}
console.log("所有路由",routes)
const router = new VueRouter({
    mode: 'history', /* base: process.env.BASE_URL,*/
    routes
})
export default router
