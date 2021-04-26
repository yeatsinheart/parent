import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewPayChannel {
    //null
    async findViewPayChannelPagingForClient(params){
    //{payTypeId:String,}
        let http_request = API.getRequest(227, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async getPayChannelConfig(params){
    //{id:Integer,}
        let http_request = API.getRequest(228, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewPayChannelAPI = new ViewPayChannel()
export default ViewPayChannelAPI
