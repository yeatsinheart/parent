import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class UserLog {
    //查询用户注册日志
    async queryRegistLogList(params) {
        //{moduleCode:String,moduleName:String,userName:String,bUserId:Integer,state:Integer,logType:Integer,address:String,createBeg:String,createEnd:String,}
        let http_request = API.getRequest(236, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询用户登录日志
    async queryLoginLogList(params) {
        //{moduleCode:String,moduleName:String,userName:String,bUserId:Integer,state:Integer,logType:Integer,address:String,createBeg:String,createEnd:String,}
        let http_request = API.getRequest(237, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询用户操作日志
    async queryOperateLogList(params) {
        //{moduleCode:String,moduleName:String,userName:String,bUserId:Integer,state:Integer,logType:Integer,address:String,createBeg:String,createEnd:String,}
        let http_request = API.getRequest(240, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //添加日志
    async addOperateLog(params) {
        //{moduleCode:String,moduleName:String,userName:String,bUserId:Integer,state:Integer,logType:Integer,address:String,createBeg:String,createEnd:String,}
        let http_request = API.getRequest(241, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const UserLogAPI = new UserLog()
export default UserLogAPI
