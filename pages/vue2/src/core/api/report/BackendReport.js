import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class BackendReport {
    //盈亏报表
    async totalWinlose(params){
    //{begTimeMill:String,endTimeMill:String,userName:String,isContains:Integer,userNameList:java.util.List,bizAppid:Integer,gameBigType:Integer,}
        let http_request = API.getRequest(491, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //盈亏日报表
    async daylyWinlose(params){
    //{begTimeMill:String,endTimeMill:String,userName:String,isContains:Integer,userNameList:java.util.List,bizAppid:Integer,gameBigType:Integer,}
        let http_request = API.getRequest(492, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //彩种盈亏报表
    async gameWinlose(params){
    //{begTimeMill:String,endTimeMill:String,userName:String,isContains:Integer,userNameList:java.util.List,bizAppid:Integer,gameBigType:Integer,}
        let http_request = API.getRequest(493, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //用户输赢报表
    async paihangbang(params){
    //{begTimeMill:String,endTimeMill:String,type:Integer,count:Integer,bizAppid:Integer,gameBigType:Integer,}
        let http_request = API.getRequest(494, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //充值留存分析
    async chargeRemain(params){
    //{begTimeMill:String,endTimeMill:String,userName:String,isContains:Integer,userNameList:java.util.List,bizAppid:Integer,gameBigType:Integer,}
        let http_request = API.getRequest(495, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const BackendReportAPI = new BackendReport()
export default BackendReportAPI
