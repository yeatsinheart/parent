import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewPayType {
    //查看支付视图列表
    async findViewPayTypePaging(params){
    //{id:Integer,typeName:String,payTypeId:Integer,subType:Integer,isShow:Boolean,createTimeBeg:Long,createTimeEnd:Long,}
        let http_request = API.getRequest(143, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async findViewPayTypePagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(144, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看支付视图详情
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(145, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑支付视图
    async updateByPrimaryKeySelective(params){
    //{id:Integer,typeName:String,sort:Integer,img:String,imgThumb:String,subType:Integer,deleted:Boolean,isShow:Boolean,}
        let http_request = API.getRequest(146, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //显示/隐藏支付视图
    async updateViewPayTypeIsShowById(params){
    //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(147, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewPayTypeAPI = new ViewPayType()
export default ViewPayTypeAPI
