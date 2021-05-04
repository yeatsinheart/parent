import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class AppMessage {
    //发送app推送消息
    async sendAppMessage(params) {
        //{id:Integer,msgContent:String,createTime:LocalDateTime,msgTitle:String,createUserId:Integer,msgType:String,msgTarget:String,sendDate:LocalDateTime,createUserName:String,}
        let http_request = API.getRequest(219, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户读取app推送消息(标为已经读取)
    async readAppMessage(params) {
        //{appMessageId:Integer,userId:Integer,}
        let http_request = API.getRequest(220, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户的app推送消息全部标为已经读取
    async readAppMessageByUserId(params) {
        //{userId:Integer,}
        let http_request = API.getRequest(221, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询用户app推送消息(会添加user的app推送消息通知)
    async queryAppMessageByUserId(params) {
        //{userId:Integer,state:Boolean,equipName:String,}
        let http_request = API.getRequest(222, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户删除所有app推送消息（清空app推送消息）
    async deleteAppMessageByUserId(params) {
        //{userId:Integer,}
        let http_request = API.getRequest(223, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户逻辑删除某个app推送消息
    async deleteLogicAppMessage(params) {
        //{appMessageId:Integer,}
        let http_request = API.getRequest(224, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台管理系统直接删除app推送消息（用户不再接受到消息，同时也不能查询消息记录）
    async deleteAppMessage(params) {
        //{appMessageId:Integer,}
        let http_request = API.getRequest(226, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const AppMessageAPI = new AppMessage()
export default AppMessageAPI
