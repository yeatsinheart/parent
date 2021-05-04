import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class DividendBill {
    //查询分红单
    async queryDividendBillList(params) {
        //{userName:String,state:Integer,startTime:String,endTime:String,idList:java.util.List,}
        let http_request = API.getRequest(404, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改分红单状态
    async batchUpdateByIdList(params) {
        //{userName:String,state:Integer,startTime:String,endTime:String,idList:java.util.List,}
        let http_request = API.getRequest(405, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const DividendBillAPI = new DividendBill()
export default DividendBillAPI
