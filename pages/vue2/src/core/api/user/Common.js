import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class Common {
    //添加群组配置
    async addGroupConfig(params){
    //{id:Integer,name:String,type:String,state:Short,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,}
        let http_request = API.getRequest(212, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询群组配置
    async queryGroupConfig(params){
    //{id:Integer,name:String,type:String,state:Short,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,}
        let http_request = API.getRequest(216, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除群组配置
    async deleteGroupConfig(params){
    //{id:Integer,name:String,type:String,state:Short,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,}
        let http_request = API.getRequest(217, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改群组配置
    async updateGroupConfig(params){
    //{id:Integer,name:String,type:String,state:Short,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,}
        let http_request = API.getRequest(218, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询群组基本信息列表
    async queryGroup(params){
    //{id:Integer,name:String,type:String,state:Short,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,}
        let http_request = API.getRequest(244, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询群组选择集合
    async queryGroupForSelect(params){
    //{id:String,type:String,bUserId:Integer,noJoin:String,}
        let http_request = API.getRequest(286, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const CommonAPI = new Common()
export default CommonAPI
