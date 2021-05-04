import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class UserAccount {
    //根据用户Id和账户类型查询
    async queryAccountByUserIdAndType(params) {
        //{money:BigDecimal,accountId:Long,createTime:LocalDateTime,version:Long,type:String,state:Integer,currency:String,userId:Integer,appId:Integer,lastUpdateTime:LocalDateTime,}
        let http_request = API.getRequest(206, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //根据用户id查询账户余额
    async queryAccountByUserId(params) {
        //{money:BigDecimal,accountId:Long,createTime:LocalDateTime,version:Long,type:String,state:Integer,currency:String,userId:Integer,appId:Integer,lastUpdateTime:LocalDateTime,}
        let http_request = API.getRequest(207, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //代理转账
    async upAndDownMoneyChange(params) {
        //{changeBalance:BigDecimal,toUserId:Integer,password:String,}
        let http_request = API.getRequest(634, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const UserAccountAPI = new UserAccount()
export default UserAccountAPI
