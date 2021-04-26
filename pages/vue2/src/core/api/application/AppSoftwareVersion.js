import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class AppSoftwareVersion {
    //获取最新版本信息
    async getNewVersion(params){
    //{h5UserClient:String,version:String,}
        let http_request = API.getRequest(496, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //下载统计
    async downloadCount(params){
    //{h5UserClient:String,version:String,}
        let http_request = API.getRequest(497, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //批量插入下载地址
    async batchInsertSelective(params){
    //
        let http_request = API.getRequest(498, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //插入版本信息
    async insertSelective(params){
    //{clientType:Integer,dtype:Integer,url:String,validTime:String,icon:String,title:String,content:String,updateType:Integer,version:String,status:Integer,downloadList:java.util.List,androidurl:String,}
        let http_request = API.getRequest(500, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑版本信息
    async updateSelective(params){
    //{id:Integer,dtype:Integer,url:String,validTime:String,icon:String,title:String,content:String,updateType:Integer,status:Integer,version:String,downloadList:java.util.List,androidurl:String,}
        let http_request = API.getRequest(605, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //分页查询
    async findAppSoftVersionPaging(params){
    //{clientType:Integer,title:String,updateType:Integer,version:String,status:Integer,}
        let http_request = API.getRequest(606, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看详情
    async getById(params){
    //{id:Integer,}
        let http_request = API.getRequest(607, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改状态
    async updateStatusById(params){
    //{id:Integer,status:Integer,}
        let http_request = API.getRequest(608, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除
    async deleteById(params){
    //{id:Integer,}
        let http_request = API.getRequest(609, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const AppSoftwareVersionAPI = new AppSoftwareVersion()
export default AppSoftwareVersionAPI
