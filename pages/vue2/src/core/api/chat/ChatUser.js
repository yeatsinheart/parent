import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ChatUser {
    //创建聊天室用户
    async createUser(params) {
        //{code:String,}
        let http_request = API.getRequest(568, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改聊天室用户信息
    async updateUser(params) {
        //{chatUserId:Integer,code:String,name:String,icon:String,sign:String,email:String,birth:String,mobile:String,gender:Integer,}
        let http_request = API.getRequest(569, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //封禁聊天室用户
    async disableUser(params) {
        //{chatUserId:Integer,needkick:Boolean,code:String,}
        let http_request = API.getRequest(570, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //解封聊天室用户
    async unblockUser(params) {
        //{chatUserId:Integer,needkick:Boolean,code:String,}
        let http_request = API.getRequest(571, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ChatUserAPI = new ChatUser()
export default ChatUserAPI
