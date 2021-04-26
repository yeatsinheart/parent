import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewQuick {
    //查看快捷栏分页
    async findViewQuickPaging(params){
    //{showClient:String,}
        let http_request = API.getRequest(168, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增快捷栏
    async insertSelective(params){
    //{name:String,clientType:String,img:String,jumpType:Integer,sort:Integer,url:String,link:String,}
        let http_request = API.getRequest(169, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑快捷栏
    async updateByPrimaryKeySelective(params){
    //{id:Integer,name:String,url:String,clientType:String,img:String,jumpType:Integer,sort:Integer,link:String,}
        let http_request = API.getRequest(170, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除快捷栏
    async deleteByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(171, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看详情
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(172, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewQuickAPI = new ViewQuick()
export default ViewQuickAPI
