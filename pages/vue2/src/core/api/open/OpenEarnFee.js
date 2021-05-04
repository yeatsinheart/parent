import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class OpenEarnFee {
    //分页获取应收账款数据
    async findOpenEarnFeePaging(params) {
        //{startTime:String,endTime:String,platformAccount:String,platformName:String,gameMaker:String,paymentType:String,status:Integer,type:String,}
        let http_request = API.getRequest(540, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //新增应收账款数据
    async insertSelective(params) {
        //{receivableDate:String,amountReceivable:BigDecimal,crossReceiveCurrency:String,settlementAmount:BigDecimal,actualAmountReceived:BigDecimal,collectionDate:String,collectionPayments:String,platformName:String,platformAccount:String,paymentType:String,amountsPayable:BigDecimal,settlementCurrency:String,exchangeRate:BigDecimal,discountAmount:BigDecimal,detail:String,note:String,status:Integer,}
        let http_request = API.getRequest(541, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改应收账款数据
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,receivableDate:String,amountReceivable:BigDecimal,crossReceiveCurrency:String,settlementAmount:BigDecimal,actualAmountReceived:BigDecimal,collectionDate:String,collectionPayments:String,platformName:String,platformAccount:String,paymentType:String,settlementCurrency:String,exchangeRate:BigDecimal,discountAmount:BigDecimal,detail:String,note:String,status:Integer,}
        let http_request = API.getRequest(542, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const OpenEarnFeeAPI = new OpenEarnFee()
export default OpenEarnFeeAPI
