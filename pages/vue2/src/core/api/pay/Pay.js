import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class Pay {
    //订单支付
    async recharge(params){
    //{orderId:Integer,orderId:Integer,orderNo:String,playerId:Integer,playerName:String,rechargeLevel:String,vipLevel:String,merchantId:Integer,currencyType:String,type:String,typeCode:String,submitAmount:Integer,actualAmount:BigDecimal,rechargeFee:BigDecimal,amountCredited:BigDecimal,isFirst:Byte,channelType:Byte,channelRatio:BigDecimal,wayId:Integer,wayCode:String,relationTypeId:Integer,channelNo:String,result:String,accountId:Integer,accountType:String,account:String,playerCardName:String,playerCardNo:String,status:Byte,createTime:LocalDateTime,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,list:java.util.List,bankCode:String,accountno:String,accountname:String,}
        let http_request = API.getRequest(243, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //出款代付
    async remit(params){
    //{orderId:Integer,wayId:Integer,wayCode:String,walletId:String,orderId:Integer,orderNo:String,playerId:Integer,playerName:String,rechargeLevel:String,vipLevel:String,merchantId:Integer,submitAmount:Integer,balance:BigDecimal,fee:BigDecimal,manageFee:BigDecimal,discountFee:BigDecimal,actualAmount:BigDecimal,isFirst:Byte,cardId:Integer,cardNo:String,bankName:String,currencyType:String,status:Byte,createTime:LocalDateTime,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,type:Byte,wayId:Integer,wayCode:String,walletId:String,result:String,processFee:BigDecimal,systemSuggest:String,count:Integer,}
        let http_request = API.getRequest(380, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询代付结果
    async queryRemitStatus(params){
    //{orderId:Integer,orderId:Integer,orderNo:String,playerId:Integer,playerName:String,rechargeLevel:String,vipLevel:String,merchantId:Integer,submitAmount:Integer,balance:BigDecimal,fee:BigDecimal,manageFee:BigDecimal,discountFee:BigDecimal,actualAmount:BigDecimal,isFirst:Byte,cardId:Integer,cardNo:String,bankName:String,currencyType:String,status:Byte,createTime:LocalDateTime,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,type:Byte,wayId:Integer,wayCode:String,walletId:String,result:String,processFee:BigDecimal,systemSuggest:String,count:Integer,}
        let http_request = API.getRequest(447, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const PayAPI = new Pay()
export default PayAPI
