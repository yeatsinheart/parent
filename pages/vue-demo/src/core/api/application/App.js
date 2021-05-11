import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class App {
    //获取站点基本信息
    async getAppInfo(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(467, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //新增站点
    async insertSelective(params) {
        //{id:Integer,ownerId:Integer,ownerName:String,ownerSecret:String,name:String,type:Integer,langCode:java.util.List,currencyList:java.util.List,countCurrency:String,country:String,minGroup:Integer,maxGroup:Integer,inviteId:Integer,inviteLevelId:Integer,activeTime:String,activeTimeDate:LocalDateTime,status:Integer,memo:String,invitedName:String,openFee:BigDecimal,netFee:BigDecimal,isNetOrPayfee:Boolean,govPayFeeList:java.util.List,selfPayFeeList:java.util.List,monthGameRecord:Long,serviceType:String,serviceName:String,financialType:String,financialName:String,serviceMail:String,financialMail:String,netMail:String,fileAddress:String,logo:String,domainList:java.util.List,defaultlanguage:String,defaultcurrency:String,}
        let http_request = API.getRequest(507, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看站点列表
    async findAppPaging(params) {
        //{platformName:String,platformAccount:String,status:Integer,}
        let http_request = API.getRequest(508, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看站点
    async selectByPrimaryKeyForBack(params) {
        //{id:Integer,}
        let http_request = API.getRequest(509, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑站点
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,ownerId:Integer,ownerName:String,ownerSecret:String,name:String,type:Integer,langCode:java.util.List,currencyList:java.util.List,countCurrency:String,country:String,minGroup:Integer,maxGroup:Integer,inviteId:Integer,inviteLevelId:Integer,activeTime:String,activeTimeDate:LocalDateTime,status:Integer,memo:String,invitedName:String,openFee:BigDecimal,netFee:BigDecimal,isNetOrPayfee:Boolean,govPayFeeList:java.util.List,selfPayFeeList:java.util.List,monthGameRecord:Long,serviceType:String,serviceName:String,financialType:String,financialName:String,serviceMail:String,financialMail:String,netMail:String,fileAddress:String,logo:String,domainList:java.util.List,defaultlanguage:String,defaultcurrency:String,}
        let http_request = API.getRequest(510, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站点类型枚举
    async selectAppTypeForSelect(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(511, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //联系方式枚举
    async selectLinkForSelect(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(513, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //收费方式枚举
    async selectPayTypeForSelect(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(514, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const AppAPI = new App()
export default AppAPI
