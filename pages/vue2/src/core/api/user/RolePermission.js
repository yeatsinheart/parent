import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class RolePermission {
    //给角色添加权限
    async addRolePermission(params){
    //{roleId:Integer,permissionsCodes:java.util.List,}
        let http_request = API.getRequest(258, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //给角色删除权限
    async deleteRolePermission(params){
    //{roleId:Integer,}
        let http_request = API.getRequest(259, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询角色权限
    async queryRolePermission(params){
    //{roleId:Integer,}
        let http_request = API.getRequest(260, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const RolePermissionAPI = new RolePermission()
export default RolePermissionAPI
