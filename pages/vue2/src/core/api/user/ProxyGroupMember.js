import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ProxyGroupMember {
    //添加代理的用户
    async addProxyGroupMember(params){
    //
        let http_request = API.getRequest(290, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除代理的用户
    async deleteProxyGroupMemberById(params){
    //
        let http_request = API.getRequest(292, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询代理下的用户
    async GroupGroupMemberByGroupId(params){
    //
        let http_request = API.getRequest(295, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //变更用户上级
    async updateMemberParent(params){
    //
        let http_request = API.getRequest(298, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询自己的下级用户
    async queryGroupMebmer(params){
    //{userId:Integer,downUserName:String,startTime:String,endTime:String,minMoney:Double,maxMoney:Double,}
        let http_request = API.getRequest(428, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ProxyGroupMemberAPI = new ProxyGroupMember()
export default ProxyGroupMemberAPI
