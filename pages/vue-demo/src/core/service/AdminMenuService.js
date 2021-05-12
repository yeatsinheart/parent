/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/
class AdminMenuService {

    async getMenuResource() {
        let menuResource = [
            {
                "id": 1,
                "icon": "el-icon-edit-outline",
                "name": "境内",
                "path": "0"
            },
            {
                "id": 2,
                "icon": "el-icon-edit-outline",
                "name": "境外",
                "path": "0"
            },
            {
                "id": 3,
                "icon": "el-icon-edit-outline",
                "name": "百度",
                "url": "https://www.baidu.com",
                "path": "0->1"
            },
            {
                "id": 4,
                "icon": "el-icon-edit-outline",
                "name": "百度文库",
                "url": "https://www.baidu.com",
                "path": "0->1->3"
            },
            {
                "id": 5,
                "icon": "el-icon-edit-outline",
                "name": "谷歌",
                "url": "https://www.google.com",
                "path": "0->2"
            }
        ]
        return menuResource
    }

    async getMenu() {
        let menu = [];
        this.getMenuResource().then(response => {

            response.forEach(o => {
                console.log("待操作资源", o)
                let path = o.path;
                let idpath = path.split("->");
                let tmp = menu;
                // 遍历族谱
                for (let i = 0; i < idpath.length; i++) {
                    let parentid = idpath[i];
                    let parentNode = null;
                    console.log("查找" + i + "节点", tmp)
                    if (tmp.length === 0) {
                        console.log("空节点直接插入", tmp)
                        this.pushResource(tmp, o);
                        continue;
                    }
                    if (parentid == 0 && idpath.length > 1) {
                        console.log("根节点直接跳过")
                        continue;
                    }
                    for (let j = 0; j < tmp.length; j++) {
                        let t = tmp[j];
                        console.log("是否父节点", t, parentid, t["id"] == parentid)
                        if (t["id"] == parentid) {
                            parentNode = t;
                        }
                    }
                    if (!parentNode) {
                        console.log("没有父节点直接插入", tmp)
                        this.pushResource(tmp, o);
                    } else {
                        console.log("存在父节点", tmp)
                        if (i === idpath.length - 1) {
                            console.log("有父节点插入子节点", parentNode, i === idpath.length - 1)
                            if (!parentNode["subs"]) {
                                parentNode["subs"] = []
                            }
                            this.pushResource(parentNode["subs"], o);
                        } else {
                            tmp = parentNode["subs"];
                            console.log("进入下一层查询", tmp)
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

    getNodeByPath(idpath) {
        this.getMenu().then(menu => {
            let targat = null;
            menu.forEach(node => {
                if (node["id"] == idpath[0]) {
                    targat = node;
                }
            })
            let o = this.findNode(targat["subs"], idpath.splice(0, 1))
            console.log("点击", o)
        })
    }

    findNode(arr, path) {
        let copypath = JSON.parse(JSON.stringify(path));
        console.log("查找点击", arr, path)
        for (let i = 0; i < arr.length; i++) {
            let node = arr[i];
            if (node["id"] == path[0]) {
                if (path.length === 1) {
                    console.log("找到咯", node)
                    return node;
                } else {
                    copypath.splice(0, 1)
                    return this.findNode(node["subs"], copypath)
                }
            }
        }
    }

}

const adminMenuService = new AdminMenuService()

export default adminMenuService
