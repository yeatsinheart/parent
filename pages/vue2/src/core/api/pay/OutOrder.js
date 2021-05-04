import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class OutOrder {
    //创建人工出款订单
    async createOrder(params) {
        //{account:String,playerId:Integer,balance:BigDecimal,amount:BigDecimal,type:String,frontNote:String,backstageNote:String,}
        let http_request = API.getRequest(344, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查找人工出款订单
    async selectOrderList(params) {
        //{startTime:String,endTime:String,orderNo:String,type:String,status:java.util.List,createUserName:String,updateUserName:String,}
        let http_request = API.getRequest(345, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //审核人工出款订单
    async verifyIncomeOrder(params) {
        //{incomeId:Integer,status:Byte,playerId:Integer,}
        let http_request = API.getRequest(346, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const OutOrderAPI = new OutOrder()
export default OutOrderAPI
