import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewBanner {
    //分页查询
    async findViewBannerPaging(params){
    //{isShow:Boolean,showClient:String,createTimeBeg:String,createTimeEnd:String,}
        let http_request = API.getRequest(116, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async findViewBannerPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(117, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除轮播图
    async deleteByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(118, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增轮播图
    async insertSelective(params){
    //{title:String,content:String,sort:Integer,img:String,position:String,isShow:Boolean,url:String,imgThumb:String,ownerId:Integer,appIdBiz:Integer,langId:String,jumpType:Integer,showClient:String,}
        let http_request = API.getRequest(119, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //批量删除轮播图
    async batchDeleteByIds(params){
    //{java.util.List:java.util.List,}
        let http_request = API.getRequest(120, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看轮播图详情
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(121, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑轮播图
    async updateByPrimaryKeySelective(params){
    //{id:Integer,title:String,content:String,sort:Integer,img:String,position:String,isShow:Boolean,url:String,imgThumb:String,ownerId:Integer,jumpType:Integer,showClient:String,deleted:Boolean,}
        let http_request = API.getRequest(122, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //轮播图隐藏活动
    async updateViewBannerIsShowById(params){
    //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(123, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取内链下拉
    async selectInnerLinkForSelect(params){
    //{showClient:String,}
        let http_request = API.getRequest(289, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取跳转类型下拉
    async selectJumpTypeForSelect(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(297, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewBannerAPI = new ViewBanner()
export default ViewBannerAPI
