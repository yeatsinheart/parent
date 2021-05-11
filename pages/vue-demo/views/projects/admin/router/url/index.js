//  "URL": { "版型标识": "添加到default后的后缀(可以是版本号)", "default": "实际页面文件地址" },
const url = {
    "/": {"page": {"default": "login.vue"}, "meta": {"needLogin": true, "withHeader": true, "withFooter": true}},
    "/found": {
        "page": {"default": "home/home.vue"},
        "meta": {"needLogin": true, "withHeader": true, "withFooter": true}
    },
    "/welfare": {
        "page": {"default": "home/home.vue"},
        "meta": {"needLogin": true, "withHeader": true, "withFooter": true}
    },
    "/login": {
        "page": {"default": "login.vue"},
        "meta": {"needLogin": false, "withHeader": false, "withFooter": false}
    },
    "/user": {"page": {"default": "user/user.vue"}, "meta": {"needLogin": true, "withHeader": true, "withFooter": true}}
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
        obj.component = () => import(`@/views/admin/${realPagePath}`);
        return obj;
    } else {
        return null;
    }
}

export {url, getRoute}