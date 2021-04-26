import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewMobileStart {
    //新增
    async insertSelective(params){
    //{title:String,sort:Integer,img:String,isShow:Boolean,langId:String,begTime:String,endTime:String,createTime:LocalDateTime,createUid:Integer,updateTime:LocalDateTime,updateUid:Integer,deleted:Boolean,version:Integer,createName:String,updateName:String,}
        let http_request = API.getRequest(612, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑
    async updateByPrimaryKey(params){
    //{id:Integer,title:String,sort:Integer,img:String,isShow:Boolean,langId:String,begTime:String,endTime:String,createTime:LocalDateTime,createUid:Integer,updateTime:LocalDateTime,updateUid:Integer,deleted:Boolean,version:Integer,createName:String,updateName:String,}
        let http_request = API.getRequest(613, API.version,params,false,API.cacheTime)
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
        let http_request = API.getRequest(614, API.version,params,false,API.cacheTime)
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
        let http_request = API.getRequest(615, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //显示隐藏
    async updateIsShowByPrimaryKey(params){
    //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(616, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取近期生效的3个启动页广告图
    async findViewMobileStartForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(617, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //分页查看
    async findViewMobileStartPaging(params){
    //{id:Integer,title:String,sort:Integer,img:String,isShow:Boolean,begTime:LocalDateTime,endTime:LocalDateTime,createTime:LocalDateTime,createUid:Integer,updateTime:LocalDateTime,updateUid:Integer,deleted:Boolean,version:Integer,createName:String,updateName:String,}
        let http_request = API.getRequest(618, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewMobileStartAPI = new ViewMobileStart()
export default ViewMobileStartAPI
