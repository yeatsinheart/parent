import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Commission {
    //查询佣金统计
    async queryCommissionUser(params) {
        //{username:String,starTime:String,endTime:String,state:Short,commissionInfoType:Short,jobQuery:Boolean,}
        let http_request = API.getRequest(339, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //佣金统计批量修改的接口
    async updateCommissionStateList(params) {
        //{state:Short,commissionUserIds:java.util.List,}
        let http_request = API.getRequest(414, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //佣金统计修改一个的状态
    async updateCommissionState(params) {
        //{id:Integer,createTime:LocalDateTime,BUserId:Integer,commissionGroupId:Integer,amount:BigDecimal,updateTime:LocalDateTime,state:Short,operationId:Integer,operationName:String,operationTime:LocalDateTime,remark:String,commissionInfoType:Short,commissionInfoGrantType:Short,proxyGroupId:Long,proxyGroupName:String,username:String,isDel:Boolean,appId:Integer,level:Integer,pUserId:Integer,currency:String,validUser:Integer,validBetting:BigDecimal,chargeAmout:BigDecimal,meAmount:BigDecimal,teamAmount:BigDecimal,}
        let http_request = API.getRequest(417, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const CommissionAPI = new Commission()
export default CommissionAPI
