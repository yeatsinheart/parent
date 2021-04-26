import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class CardBlackList {
    //银行卡添加黑名单
    async addBlackBankCard(params){
    //{id:Integer,cardNum:String,bankType:String,remark:String,}
        let http_request = API.getRequest(272, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除银行卡黑名单
    async delBackCardList(params){
    //{id:Integer,cardNum:String,bankType:String,remark:String,}
        let http_request = API.getRequest(273, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取黑名单银行卡
    async queryBlackCardList(params){
    //{id:Integer,cardNum:String,bankType:String,remark:String,}
        let http_request = API.getRequest(274, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改黑名单银行卡
    async updateBlackCard(params){
    //{id:Integer,cardNum:String,bankType:String,remark:String,}
        let http_request = API.getRequest(435, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const CardBlackListAPI = new CardBlackList()
export default CardBlackListAPI
