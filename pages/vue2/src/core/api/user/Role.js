import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Role {
    //添加角色
    async addRole(params) {
        //{id:Integer,roleName:String,parentRoleId:Integer,createTime:LocalDateTime,type:Integer,appId:Integer,remark:String,secretAttribute:String,}
        let http_request = API.getRequest(192, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //通过角色id删除角色
    async deleteByRoleId(params) {
        //{roleId:Integer,}
        let http_request = API.getRequest(193, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改角色
    async updateRole(params) {
        //{id:Integer,roleName:String,parentRoleId:Integer,createTime:LocalDateTime,type:Integer,appId:Integer,remark:String,secretAttribute:String,}
        let http_request = API.getRequest(194, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //根据角色id查询角色
    async selectByRoleCode(params) {
        //{roleId:Integer,}
        let http_request = API.getRequest(195, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询所有角色列表
    async queryRoleList(params) {
        //{id:Integer,roleName:String,parentRoleId:Integer,createTime:LocalDateTime,type:Integer,appId:Integer,remark:String,secretAttribute:String,}
        let http_request = API.getRequest(213, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const RoleAPI = new Role()
export default RoleAPI
