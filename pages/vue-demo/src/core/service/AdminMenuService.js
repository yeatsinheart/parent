/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/
import store from '@/store'

class AdminMenuService {

    tabid = 0;

     insertMenu(menu,node){
         if (node['parent']===0) {
             menu.push(node);
             return;
             //this.pushResource(tmp, o);
         }
         for(let i =0;i<menu.length;i++){
             let menuNode = menu[i];
             if(menuNode['id']===node['parent']){
                 if (!menuNode["subs"]) {
                     menuNode["subs"] = []
                 }
                 menuNode["subs"].push(node);
             }else{
                 if (!menuNode["subs"]) {
                     // 没有下级直接跳过
                     continue;
                 }
                 this.insertMenu(menuNode['subs'],node)
             }
         }
    }
    async getMenu() {
        // 节点的父节点ID必然更小
        // 节点的父节点肯定已经放入menu中
        let menu = [];
        this.getMenuResource().then(response => {
            response.forEach(node => {
                this.insertMenu(menu,node);
            })
        })
        return menu;
    }
/*

    findNode(menu, path) {
        let copypath = JSON.parse(JSON.stringify(path));
        for (let i = 0; i < menu.length; i++) {
            let node = menu[i];
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
*/

    async getMenuResource() {
        let menuResource = [
            {
                "id": 1,
                "name": "后台管理",
                "parent": 0
            },
            {
                "id": 2,
                "name": "账号管理",
                "url": "admin",
                "parent": 1
            },
            {
                "id": 3,
                "name": "岗位管理",
                "url": "role",
                "parent": 1
            },
            {
                "id": 4,
                "name": "资源管理",
                "url": "resource",
                "parent": 1

            },
            {
                "id": 5,
                "name": "登陆注册",
                "url": "login",
                "parent": 0
            },
            {
                "id": 6,
                "name": "语言翻译",
                "url": "language",
                "parent": 0
            },
            {
                "id": 7,
                "name": "租户管理",
                "url": "login",
                "parent": 0
            },
            {
                "id": 8,
                "name": "租户列表",
                "url": "login",
                "parent": 7
            },
            {
                "id": 9,
                "name": "APP管理",
                "url": "login",
                "parent": 7
            },
            {
                "id": 9,
                "name": "域名列表",
                "url": "login",
                "parent": 7
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
