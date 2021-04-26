import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class SysAdminUser {
    //系统商登录
    async login(params){
    //{userName:String,password:String,verifyCode:String,}
        let http_request = API.getRequest(524, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增系统商用户
    async addAdminUser(params){
    //{bUserId:Integer,userName:String,password:String,phone:String,roleId:Integer,}
        let http_request = API.getRequest(525, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //刪除系统商用户
    async deleteAdminUser(params){
    //{id:Integer,}
        let http_request = API.getRequest(526, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询系统商用户列表
    async queryAdminUserList(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(527, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改系统商用户
    async updateAdminUser(params){
    //{bUserId:Integer,userName:String,password:String,phone:String,roleId:Integer,}
        let http_request = API.getRequest(528, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //系统商，用户登出
    async loginOut(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(551, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const SysAdminUserAPI = new SysAdminUser()
export default SysAdminUserAPI
