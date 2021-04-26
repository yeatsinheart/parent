import Vue from 'vue'
import VueRouter from 'vue-router'
//if (!window.VueRouter)
Vue.use(VueRouter)

import { url, getRoute } from '@/url/index'

// 可以定义特殊路由，需要meta信息的路由
let routes = [
]


// //版型-客户端界面
/*
require.context()
创建自己的（模块）上下文，这个方法有 3 个参数：
要搜索的文件夹目录
是否还应该搜索它的子目录，
以及一个匹配文件的正则表达式。
*/
//所有项目都有的URL

//编译时已经默认了
for (var key in url) {
    //frame 切换
    let frame = localStorage.getItem('choosed-frame') || localStorage.getItem('application-default-frame') || process.env.VUE_APP_DEFAULT_FRAME
    let filePath = getRoute(key, frame)
    let metaInfo = {}
    console.log(`@/views/${filePath}`);
    if (filePath) {
        routes.push({
            path: key, 
            component: () => import(`@/views/${filePath}`), 
            meta: metaInfo
        })
    }
}
console.log("路由",routes)

const router = new VueRouter({
    mode: 'history', /* base: process.env.BASE_URL,*/
    routes
})
export default router