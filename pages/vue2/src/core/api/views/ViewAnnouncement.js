import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewAnnouncement {
    //分页查询
    async findViewAnnouncementPaging(params){
    //{title:String,type:String,allowedGroup:Integer,showClient:String,langId:String,isShow:Boolean,begTimeBeg:String,begTimeEnd:String,endTimeBeg:String,endTimeEnd:String,}
        let http_request = API.getRequest(108, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async findViewAnnouncementPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(109, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除公告
    async deleteByPrimaryKey(params){
    //{idList:java.util.List,}
        let http_request = API.getRequest(110, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增公告
    async insertSelective(params){
    //{title:String,content:String,type:String,timeStar:Long,timeEnd:Long,allowedGroup:Integer,showClient:String,ownerId:Integer,appIdBiz:Integer,langId:String,summary:String,isShow:Boolean,sort:Integer,}
        let http_request = API.getRequest(111, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //批量删除公告
    async batchDeleteByIds(params){
    //{java.util.List:java.util.List,}
        let http_request = API.getRequest(112, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看公告详情
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(113, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑公告
    async updateByPrimaryKeySelective(params){
    //{id:Integer,title:String,content:String,type:String,timeStar:Long,timeEnd:Long,allowedGroup:Integer,showClient:String,langId:String,summary:String,isShow:Boolean,sort:Integer,}
        let http_request = API.getRequest(114, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //显示隐藏公告
    async updateViewAnnouncementIsShowById(params){
    //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(115, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewAnnouncementAPI = new ViewAnnouncement()
export default ViewAnnouncementAPI
