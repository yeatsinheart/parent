import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewMenu {
    //null
    async findViewMenuPaging(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(139, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async findAllAndOwnedMenuByRoleId(params) {
        //{roleId:Integer,}
        let http_request = API.getRequest(140, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async findDisplayMenuByRoleId(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(141, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑角色权限
    async batchInsert(params) {
        //{roleId:Integer,menuList:java.util.List,actionList:java.util.List,bizAppid:Integer,}
        let http_request = API.getRequest(142, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看并且同步菜单（系统商后台）
    async findAllAndOwnedMenuByAppid(params) {
        //{roleId:Integer,menuList:java.util.List,actionList:java.util.List,bizAppid:Integer,}
        let http_request = API.getRequest(651, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑站点菜单（系统商后台）
    async batchInsertForAppid(params) {
        //{roleId:Integer,menuList:java.util.List,actionList:java.util.List,bizAppid:Integer,}
        let http_request = API.getRequest(652, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewMenuAPI = new ViewMenu()
export default ViewMenuAPI
