import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class SalaryUser {
    //后台查询分页查询日工资统计接口
    async querySalaryUser(params) {
        //{userName:String,starDate:String,endDate:String,state:Short,salaryName:String,isDown:Boolean,}
        let http_request = API.getRequest(410, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台修改一个日工资统计表的状态
    async updateSalaryUserState(params) {
        //{id:Integer,grantDate:LocalDateTime,salaryName:String,salaryGroupId:Integer,state:Short,proxyId:Integer,proxyName:String,shouldBalance:BigDecimal,setBalance:BigDecimal,realityBalance:BigDecimal,grantType:Short,proxyTier:Integer,validUserNum:Integer,teamValidBet:BigDecimal,allProfit:BigDecimal,todaySalary:BigDecimal,operationId:Integer,operationName:String,operationTime:LocalDateTime,userId:Integer,userName:String,salaryRatio:String,pUserId:Integer,currency:String,validBetting:BigDecimal,chargeAmout:BigDecimal,meAmount:BigDecimal,teamAmount:BigDecimal,}
        let http_request = API.getRequest(411, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台修改日工资统计接口（批量）
    async updateSalaryUserStateList(params) {
        //{state:Short,salaryUserIds:java.util.List,}
        let http_request = API.getRequest(412, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const SalaryUserAPI = new SalaryUser()
export default SalaryUserAPI
