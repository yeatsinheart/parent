import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewMobileMid {
    // 分页查看手机通栏
    async findViewMobileMidPaging(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(590, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    // 查看手机通栏（客户端）
    async findViewMobileMidPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(591, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增
    async insertSelective(params){
    //{sort:Integer,img:String,position:String,name:String,url:String,imgThumb:String,jumpType:Integer,}
        let http_request = API.getRequest(592, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(593, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑
    async updateByPrimaryKeySelective(params){
    //{id:Integer,sort:Integer,img:String,name:String,position:String,url:String,imgThumb:String,jumpType:Integer,}
        let http_request = API.getRequest(594, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除
    async deleteByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(595, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //手机通栏下拉
    async selectMobileMinForSelect(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(596, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewMobileMidAPI = new ViewMobileMid()
export default ViewMobileMidAPI
