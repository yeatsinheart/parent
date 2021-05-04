import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class UserRole {
    //给用户添加角色
    async addUserRole(params) {
        //{bUserId:Integer,roleIds:java.util.List,}
        let http_request = API.getRequest(197, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除用户的角色
    async deleteUserRoleByUserId(params) {
        //{bUserId:Integer,roleIds:java.util.List,}
        let http_request = API.getRequest(198, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //通过用户的id查询用户所具有的权限
    async queryRoleByUserId(params) {
        //{bUserId:Integer,roleIds:java.util.List,}
        let http_request = API.getRequest(199, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改用户的角色
    async updateUserRoleByUserId(params) {
        //{bUserId:Integer,roleIds:java.util.List,}
        let http_request = API.getRequest(300, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const UserRoleAPI = new UserRole()
export default UserRoleAPI
