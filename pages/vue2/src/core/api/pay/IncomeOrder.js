import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class IncomeOrder {
    //创建人工入款订单
    async createOrder(params){
    //{systemType:String,userAccount:String,level:String,vipLevel:String,amount:BigDecimal,checkType:Byte,checkAmount:BigDecimal,incomeType:String,frontNote:String,backstageNote:String,}
        let http_request = API.getRequest(335, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询人工入款订单
    async selectOrderList(params){
    //{startTime:String,endTime:String,orderNo:String,systemType:String,incomeType:String,status:Byte,createUserName:String,updateUserName:String,}
        let http_request = API.getRequest(336, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //审核人工入款顶单
    async verifyIncomeOrder(params){
    //{incomeId:Integer,status:Byte,playerId:Integer,}
        let http_request = API.getRequest(337, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const IncomeOrderAPI = new IncomeOrder()
export default IncomeOrderAPI
