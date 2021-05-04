import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Promoter {
    //新增推广人员
    async addPromoter(params) {
        //{id:Integer,name:String,identity:String,country:String,introducerId:Integer,introducerName:String,introducerId2:Integer,introducerName2:String,label:String,state:String,currency:String,country:String,province:String,bankCode:String,bankName:String,bankBranch:String,holderName:String,swift:String,cardNum:Integer,}
        let http_request = API.getRequest(533, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改推广人员
    async updatePromoter(params) {
        //{id:Integer,name:String,identity:String,country:String,introducerId:Integer,introducerName:String,introducerId2:Integer,introducerName2:String,label:String,state:String,currency:String,country:String,province:String,bankCode:String,bankName:String,bankBranch:String,holderName:String,swift:String,cardNum:Integer,}
        let http_request = API.getRequest(534, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //推广人员列表
    async queryPromoterList(params) {
        //{id:Integer,name:String,identity:String,state:String,type:Integer,}
        let http_request = API.getRequest(535, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //推广人员列表
    async queryPromoterListForSelect(params) {
        //{id:Integer,name:String,identity:String,state:String,type:Integer,}
        let http_request = API.getRequest(557, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const PromoterAPI = new Promoter()
export default PromoterAPI
