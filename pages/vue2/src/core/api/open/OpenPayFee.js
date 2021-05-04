import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class OpenPayFee {
    //分页获取应付账款数据
    async findOpenPayFeePaging(params) {
        //{startTime:String,endTime:String,platformAccount:String,platformName:String,gameMaker:String,paymentType:String,status:Integer,type:String,}
        let http_request = API.getRequest(543, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //新增应付账款数据
    async insertSelective(params) {
        //{dueDate:String,vendorNames:String,paymentType:String,amountsPayable:BigDecimal,settlementCurrency:String,deliveryCurrency:String,exchangeRate:BigDecimal,deliveryAmount:BigDecimal,amountPaid:BigDecimal,discountAmount:BigDecimal,paymentDate:String,paymentVoucher:String,detail:String,note:String,status:Integer,type:String,staffno:String,}
        let http_request = API.getRequest(544, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改应付账款数据
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,dueDate:String,vendorNames:String,paymentType:String,amountsPayable:BigDecimal,settlementCurrency:String,deliveryCurrency:String,exchangeRate:BigDecimal,deliveryAmount:BigDecimal,amountPaid:BigDecimal,discountAmount:BigDecimal,paymentDate:String,paymentVoucher:String,detail:String,note:String,status:Integer,staffno:String,}
        let http_request = API.getRequest(545, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const OpenPayFeeAPI = new OpenPayFee()
export default OpenPayFeeAPI
