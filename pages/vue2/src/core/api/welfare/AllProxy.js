import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class AllProxy {
    //全民推广查询我的业绩和奖励
    async myReward(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(657, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const AllProxyAPI = new AllProxy()
export default AllProxyAPI
