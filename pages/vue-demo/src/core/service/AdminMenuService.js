/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/
import store from '@/store'

class AdminMenuService {

    tabid = 0;

    async getMenu() {
        let menu = [];
        this.getMenuResource().then(response => {

            response.forEach(o => {
                let path = o.path;
                let idpath = path.split("->");
                let tmp = menu;
                // 遍历族谱
                for (let i = 0; i < idpath.length; i++) {
                    let parentid = idpath[i];
                    let parentNode = null;
                    if (tmp.length === 0) {
                        this.pushResource(tmp, o);
                        continue;
                    }
                    if (parentid == 0 && idpath.length > 1) {
                        continue;
                    }
                    for (let j = 0; j < tmp.length; j++) {
                        let t = tmp[j];
                        if (t["id"] == parentid) {
                            parentNode = t;
                        }
                    }
                    if (!parentNode) {
                        this.pushResource(tmp, o);
                    } else {
                        if (i === idpath.length - 1) {
                            if (!parentNode["subs"]) {
                                parentNode["subs"] = []
                            }
                            this.pushResource(parentNode["subs"], o);
                        } else {
                            tmp = parentNode["subs"];
                        }
                    }

                }
            })
        })
        return menu;
    }

    async pushResource(menu, resource) {
        let contained = false;
        if (!menu) {
            menu = [];
        }
        menu.forEach(o => {
            if (o["id"] === resource["id"]) {
                contained = true;
            }
        })
        if (!contained) {
            menu.push(resource);
        }
    }

    findNode(arr, path) {
        let copypath = JSON.parse(JSON.stringify(path));
        for (let i = 0; i < arr.length; i++) {
            let node = arr[i];
            if (node["id"] == path[0]) {
                if (path.length === 1) {
                    return node;
                } else {
                    copypath.splice(0, 1)
                    return this.findNode(node["subs"], copypath)
                }
            }
        }
    }

    async getMenuResource() {
        let menuResource = [
            {
                "id": 1,
                "icon": "el-icon-edit-outline",
                "name": "系统管理",
                "path": "0"
            },
            {
                "id": 2,
                "icon": "el-icon-edit-outline",
                "name": "用户管理",
                "url": "https://www.baidu.com",
                "path": "0->1"
            },
            {
                "id": 3,
                "icon": "el-icon-edit-outline",
                "name": "岗位管理",
                "url": "https://www.baidu.com",
                "path": "0->1"
            },
            {
                "id": 4,
                "icon": "el-icon-edit-outline",
                "name": "菜单管理",
                "url": "https://www.baidu.com",
                "path": "0->1"
            },
            {
                "id": 5,
                "icon": "el-icon-edit-outline",
                "name": "统计报表",
                "url": "/login",
                "path": "0"
            },
            {
                "id": 6,
                "icon": "el-icon-edit-outline",
                "name": "登陆",
                "url": "/login",
                "path": "0"
            }
        ]
        return menuResource
    }

    open(name, url) {
        this.tabid++;
        store.commit('taglist', {'id': this.tabid, 'name': name, "url": url})
    }


}

const adminMenuService = new AdminMenuService()

export default adminMenuService
