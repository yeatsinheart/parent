import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class RiskUserAmount {
    //添加流水稽核
    async addRiskUserAmountControl(params){
    //{bUserId:Integer,bUserName:String,date:String,amount:BigDecimal,targetAmount:BigDecimal,betsAmount:BigDecimal,fee:BigDecimal,type:String,}
        let http_request = API.getRequest(314, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询流水稽核
    async queryRiskUserAmountList(params){
    //{id:Integer,targetAmount:BigDecimal,userName:String,}
        let http_request = API.getRequest(315, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除流水稽核
    async deleteRiskUserAmountById(params){
    //{id:Integer,targetAmount:BigDecimal,userName:String,}
        let http_request = API.getRequest(316, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改流水稽核
    async updateRiskUserAmount(params){
    //{id:Integer,targetAmount:BigDecimal,userName:String,}
        let http_request = API.getRequest(317, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //清理流水稽核
    async clearRiskUserAmount(params){
    //{id:Integer,targetAmount:BigDecimal,userName:String,}
        let http_request = API.getRequest(319, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //用户查询自己的流水稽核
    async querySelfRisklist(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(473, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const RiskUserAmountAPI = new RiskUserAmount()
export default RiskUserAmountAPI
