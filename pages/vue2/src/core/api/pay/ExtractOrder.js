import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ExtractOrder {
    //创建出款订单
    async createExtractOrder(params){
    //{amount:BigDecimal,password:String,cardId:Integer,cardNo:String,bankName:String,}
        let http_request = API.getRequest(247, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //用户查询出款订单
    async userSelectOrderList(params){
    //{startTime:String,endTime:String,orderNo:String,statusList:java.util.List,}
        let http_request = API.getRequest(303, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //管理后台查找出款订单
    async selectOrderList(params){
    //{orderId:Integer,orderNo:String,playerId:Integer,playerName:String,minAmount:Integer,maxAmount:Integer,cardId:Integer,currencyType:String,isFirst:String,statusList:java.util.List,startTime:String,endTime:String,type:Byte,wayId:Integer,updateUserName:String,}
        let http_request = API.getRequest(308, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //审核出款订单
    async verifyOrder(params){
    //{orderId:Integer,type:Byte,status:Byte,actualAmount:BigDecimal,result:String,}
        let http_request = API.getRequest(313, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //取消出款订单
    async cancelOrder(params){
    //{orderId:Integer,result:String,}
        let http_request = API.getRequest(334, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ExtractOrderAPI = new ExtractOrder()
export default ExtractOrderAPI
