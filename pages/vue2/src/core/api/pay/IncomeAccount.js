import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class IncomeAccount {
    //查询入款账号类型
    async findIncomeAccountType(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(264, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //新增入款账号
    async createIncomeAccount(params) {
        //{typeCodeId:Integer,account:String,password:String,uid:String,name:String,bankName:String,bankCode:String,qrUrl:String,balance:BigDecimal,note:Integer,useRuleConfig:String,discountConfig:String,levelIdList:java.util.List,}
        let http_request = API.getRequest(265, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询入款账号默认使用规则和入款优惠配置
    async findDefaultConfig(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(267, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取入款账号信息和配置
    async findIncomeAccountMsg(params) {
        //{accountId:Integer,}
        let http_request = API.getRequest(305, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改入款账号信息和配置
    async updateIncomeAccount(params) {
        //{accountId:Integer,merchantId:Integer,type:String,typeCode:String,account:String,password:String,uid:String,name:String,bankName:String,bankCode:String,qrUrl:String,balance:BigDecimal,todayAmount:BigDecimal,todayCount:Integer,totalAmount:BigDecimal,useOrNot:Byte,todayUsableOrNot:Byte,usableOrNot:Byte,note:Integer,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,dayLimitAmount:Integer,payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,levelIdList:java.util.List,}
        let http_request = API.getRequest(306, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询站点的入款账号
    async selectAll(params) {
        //{accountId:Integer,merchantId:Integer,type:String,typeCode:String,account:String,name:String,bankName:String,useOrNot:Byte,todayUsableOrNot:Byte,usableOrNot:Byte,startTime:java.util.Date,endTime:java.util.Date,}
        let http_request = API.getRequest(371, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //管理入款账号是否使用
    async manageAccountUseOrNot(params) {
        //{accountId:Integer,status:Byte,}
        let http_request = API.getRequest(372, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除入款账号
    async manageAccountUsableOrNot(params) {
        //{accountId:Integer,}
        let http_request = API.getRequest(373, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询站点可用的入款账号
    async selectShowMsg(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(376, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const IncomeAccountAPI = new IncomeAccount()
export default IncomeAccountAPI
