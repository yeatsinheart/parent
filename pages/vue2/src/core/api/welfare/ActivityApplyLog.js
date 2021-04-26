import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ActivityApplyLog {
    //活动列表
    async findActivityApplyLogPaging(params){
    //{userName:String,createTimeBeg:String,createTimeEnd:String,amountBeg:BigDecimal,amountEnd:BigDecimal,status:Integer,agent:String,}
        let http_request = API.getRequest(320, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //活动审核
    async updateByPrimaryKeySelective(params){
    //{id:java.util.List,checkStatus:Integer,}
        let http_request = API.getRequest(321, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //活动统计
    async getSumByExample(params){
    //{userName:String,createTimeBeg:String,createTimeEnd:String,amountBeg:BigDecimal,amountEnd:BigDecimal,status:Integer,agent:String,}
        let http_request = API.getRequest(331, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //优惠记录
    async findActivityApplyLogPagingForClient(params){
    //{createTimeBeg:String,createTimeEnd:String,status:Integer,}
        let http_request = API.getRequest(352, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ActivityApplyLogAPI = new ActivityApplyLog()
export default ActivityApplyLogAPI
