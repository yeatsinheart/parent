import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class Message {
    //发送邮件
    async sendMessage(params){
    //{id:Integer,title:String,messageType:String,messageTarget:String,messageSource:String,createTime:LocalDateTime,content:String,messageSourceId:Integer,isDel:Boolean,isTier:Boolean,tierName:String,state:Boolean,}
        let http_request = API.getRequest(229, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //用户读取邮件（所有邮件）
    async readMessage(params){
    //{messageId:Integer,userId:Integer,}
        let http_request = API.getRequest(230, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //全部标为已经读取
    async readMessageByUserId(params){
    //{userId:Integer,}
        let http_request = API.getRequest(231, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询用户邮件(会添加user的邮件通知)
    async queryMessageByUserId(params){
    //{state:Boolean,startDate:LocalDateTime,endDate:LocalDateTime,}
        let http_request = API.getRequest(232, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除用户所有邮件（清空邮件）
    async deleteMessageByUserId(params){
    //{userId:Integer,}
        let http_request = API.getRequest(233, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //用户逻辑删除邮件
    async deleteLogicMessage(params){
    //{messageIds:java.util.List,messageId:Integer,}
        let http_request = API.getRequest(234, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //后台管理系统直接删除邮件
    async deleteMessage(params){
    //{messageIds:java.util.List,messageId:Integer,}
        let http_request = API.getRequest(235, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //后台查询用户邮件
    async queryMessage(params){
    //{targetName:String,sourceName:String,startDate:String,endDate:String,}
        let http_request = API.getRequest(266, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //后台管理系统查看邮件
    async behindReadMessage(params){
    //{messageIds:java.util.List,messageId:Integer,}
        let http_request = API.getRequest(271, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询用户发件箱
    async querySendMessageByUserId(params){
    //{state:Boolean,startDate:LocalDateTime,endDate:LocalDateTime,}
        let http_request = API.getRequest(461, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //前台用户批量删除消息
    async deleteLogicMessageList(params){
    //{messageDtos:java.util.List,}
        let http_request = API.getRequest(487, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const MessageAPI = new Message()
export default MessageAPI
