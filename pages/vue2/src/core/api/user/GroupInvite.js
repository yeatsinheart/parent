import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class GroupInvite {
    //添加邀请链接
    async addGroupInvite(params) {
        //{inviteCode:String,bonus:java.util.List,isProxy:Integer,}
        let http_request = API.getRequest(383, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改邀请链接
    async updateGroupInvite(params) {
        //{id:Integer,inviteCode:String,bonus:java.util.List,isProxy:Integer,state:Integer,}
        let http_request = API.getRequest(384, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询用户的邀请链接
    async queryGroupInviteList(params) {
        //{id:Integer,groupId:Integer,inviteCode:String,state:Integer,isProxy:Integer,appId:Integer,}
        let http_request = API.getRequest(385, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除邀请链接
    async delGroupInvite(params) {
        //{groupInviteId:Integer,}
        let http_request = API.getRequest(386, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const GroupInviteAPI = new GroupInvite()
export default GroupInviteAPI
