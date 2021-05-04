import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class PayConfig {
    //配置财务配置
    async saveFinanceConfig(params) {
        //{checkConfig:String,discountCutConfig:String,firstExtractConfig:String,vipRechargeConfig:String,bankCardConfig:String,bankCardRuleConfig:String,}
        let http_request = API.getRequest(324, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询财务配置
    async selectFinanceConfig(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(327, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //更新财务接口
    async updateFinanceConfig(params) {
        //{payConfigList:java.util.List,}
        let http_request = API.getRequest(328, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const PayConfigAPI = new PayConfig()
export default PayConfigAPI
