import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class RiskControl {
    //添加风控条件
    async addRiskControl(params) {
        //{id:Integer,type:String,symbol:String,term:String,state:Integer,}
        let http_request = API.getRequest(309, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除风控条件
    async deleteRiskControlById(params) {
        //{id:Integer,type:String,symbol:String,term:String,state:Integer,}
        let http_request = API.getRequest(310, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改风控条件
    async updateRiskControl(params) {
        //{id:Integer,type:String,symbol:String,term:String,state:Integer,}
        let http_request = API.getRequest(311, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询风控条件
    async queryRiskControl(params) {
        //{id:Integer,type:String,symbol:String,term:String,state:Integer,}
        let http_request = API.getRequest(312, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询风控条件-下拉框
    async queryRiskControlConfig(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(323, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const RiskControlAPI = new RiskControl()
export default RiskControlAPI
