import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class CostBill {
    //参与成本
    async createCostBillOrder(params) {
        //{costId:Integer,id:Integer,name:String,state:Integer,type:String,subType:String,createTime:LocalDateTime,createUserId:Integer,createUserName:String,lastUpdateTime:LocalDateTime,updateUserId:Integer,updateUserName:String,appId:Integer,statisticsTime:String,currency:String,language:String,isAllUser:Integer,}
        let http_request = API.getRequest(645, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //审核成本订单
    async verifyOrder(params) {
        //{orderIdList:java.util.List,type:String,status:Integer,setBalance:BigDecimal,}
        let http_request = API.getRequest(646, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //发放/领取成本
    async sendCost(params) {
        //{orderIdList:java.util.List,type:String,status:Integer,setBalance:BigDecimal,}
        let http_request = API.getRequest(647, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //全民代理查询我的推广
    async queryMySpread(params) {
        //{type:String,playerId:Integer,statusList:java.util.List,startTime:String,endTime:String,subUserName:String,}
        let http_request = API.getRequest(649, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //全民代理查询我的下级
    async querySubordinate(params) {
        //{type:String,playerId:Integer,statusList:java.util.List,startTime:String,endTime:String,subUserName:String,}
        let http_request = API.getRequest(650, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const CostBillAPI = new CostBill()
export default CostBillAPI
