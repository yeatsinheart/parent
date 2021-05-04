import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class RechargeWay {
    //管理接入通道是否可用
    async manageRechargeWay(params) {
        //{wayId:Integer,status:Byte,}
        let http_request = API.getRequest(418, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //管理通道的某支付方式是否可用
    async manageRechargeWayType(params) {
        //{typeCodeId:Integer,status:Byte,}
        let http_request = API.getRequest(460, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const RechargeWayAPI = new RechargeWay()
export default RechargeWayAPI
