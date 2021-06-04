import Vue from 'vue'
import VueRouter from 'vue-router'
//if (!window.VueRouter)
Vue.use(VueRouter)

const url = {
    "/": {
        "page": {"default": "home/home.vue"},
        "meta": {"needLogin": false, "withHeader": true, "withFooter": true}
    },
    "/login": {
        "page": {"default": "login/login.vue"},
        "meta": {"needLogin": false, "withHeader": false, "withFooter": false}
    },
    "/resource": {
        "page": {"default": "sys/resource/resource.vue"},
        "meta": {"needLogin": false, "withHeader": false, "withFooter": false}
    },
    "/role": {
        "page": {"default": "sys/role/role.vue"},
        "meta": {"needLogin": false, "withHeader": false, "withFooter": false}
    },
    "/admin": {
        "page": {"default": "sys/admin/admin.vue"},
        "meta": {"needLogin": false, "withHeader": false, "withFooter": false}
    }
}

function getRealPath(path, frame) {
    const obj = {};
    if (url[path]) {
        obj.path = path;
        obj.meta = url[path]["meta"]
        let realPagePath = "";
        if (url[path]["page"][frame]) {
            realPagePath = url[path]["page"]["default"] + url[path]["page"][frame];
        } else {
            realPagePath = url[path]["page"]["default"];
        }
        return realPagePath;
    } else {
        return null;
    }
}

function getRoute(path, frame) {
    const obj = {};
    if (url[path]) {
        obj.path = path;
        obj.meta = url[path]["meta"]
        let realPagePath = "";
        if (url[path]["page"][frame]) {
            realPagePath = url[path]["page"]["default"] + url[path]["page"][frame];
        } else {
            realPagePath = url[path]["page"]["default"];
        }
        obj.component = () => import(`../pages/${realPagePath}`);
        return obj;
    } else {
        return null;
    }
}


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
    base: 'admin',
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
export {getRealPath}
export default router
