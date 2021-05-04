import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class RechargeOrder {
    //创建充值订单
    async createRechargeOrder(params) {
        //{amount:Integer,channelCode:String,}
        let http_request = API.getRequest(242, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //撤销订单
    async cancelOrder(params) {
        //{orderId:Integer,result:String,}
        let http_request = API.getRequest(245, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询在途订单
    async findValidOrder(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(246, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改订单
    async updateOrderStatus(params) {
        //{orderId:Integer,status:Byte,result:String,playerCardName:String,playerCardNo:String,}
        let http_request = API.getRequest(276, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查找通道支持的银行卡
    async selectBankCodeList(params) {
        //
        let http_request = API.getRequest(287, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户查询充值订单
    async userSelectRechargeOrder(params) {
        //{startTime:String,endTime:String,orderNo:String,statusList:java.util.List,}
        let http_request = API.getRequest(301, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //订单审核（人工确认入款）
    async verifyRechargeOrder(params) {
        //{orderId:Integer,type:Byte,status:Byte,actualAmount:BigDecimal,result:String,}
        let http_request = API.getRequest(302, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //管理员查找充值订单
    async managerSelectRechargeOrder(params) {
        //{orderId:Integer,orderNo:String,playerId:Integer,playerName:String,minAmount:Integer,maxAmount:Integer,channelType:Byte,isFirst:Byte,playerCardName:String,statusList:java.util.List,startTime:String,endTime:String,relationTypeId:Integer,accountId:Integer,}
        let http_request = API.getRequest(307, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const RechargeOrderAPI = new RechargeOrder()
export default RechargeOrderAPI
