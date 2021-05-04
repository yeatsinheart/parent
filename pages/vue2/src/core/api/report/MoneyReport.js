import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class MoneyReport {
    //账变报表
    async accountOrder(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,userName:String,isContains:Integer,accountChangeTypeList:java.util.List,bizAppid:Integer,}
        let http_request = API.getRequest(489, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    // 账变导出
    async exportAccountOrder(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,userName:String,isContains:Integer,accountChangeTypeList:java.util.List,bizAppid:Integer,}
        let http_request = API.getRequest(552, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const MoneyReportAPI = new MoneyReport()
export default MoneyReportAPI
