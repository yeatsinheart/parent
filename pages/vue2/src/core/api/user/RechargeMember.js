import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class RechargeMember {
    //查询支付成员层级
    async queryRechargeMemberList(params){
    //{id:Integer,userName:String,isAccurate:Boolean,topUserName:String,startTime:String,endTime:String,state:Integer,}
        let http_request = API.getRequest(422, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //锁定支付成员层级
    async batchLockRechargeMember(params){
    //{idList:java.util.List,rechargeLevelId:Integer,changeUserId:Integer,}
        let http_request = API.getRequest(423, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //解锁支付成员层级
    async batchUnLockRechargeMember(params){
    //{idList:java.util.List,rechargeLevelId:Integer,changeUserId:Integer,}
        let http_request = API.getRequest(424, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改支付成员层级
    async batchUpdateRechargeMember(params){
    //{idList:java.util.List,rechargeLevelId:Integer,changeUserId:Integer,}
        let http_request = API.getRequest(426, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //通过用户ID，修改用户层级
    async updateRechargeMember(params){
    //{idList:java.util.List,rechargeLevelId:Integer,changeUserId:Integer,}
        let http_request = API.getRequest(475, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const RechargeMemberAPI = new RechargeMember()
export default RechargeMemberAPI
