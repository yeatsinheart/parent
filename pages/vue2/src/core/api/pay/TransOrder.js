import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class TransOrder {
    //创建余额转换订单
    async createOrder(params){
    //{inPlayerId:Integer,inMerchantId:Integer,amount:BigDecimal,}
        let http_request = API.getRequest(248, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const TransOrderAPI = new TransOrder()
export default TransOrderAPI
