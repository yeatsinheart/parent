import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ChatRoom {
    //创建聊天室
    async createRoom(params){
    //{code:String,name:String,announcement:String,}
        let http_request = API.getRequest(562, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询聊天室信息
    async selectRoom(params){
    //{chatRoomId:Integer,code:String,}
        let http_request = API.getRequest(563, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询聊天室列表
    async batchSelectRoom(params){
    //{chatRoomId:Integer,code:String,}
        let http_request = API.getRequest(564, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //更新聊天室信息
    async updateRoomInfo(params){
    //{roomId:Integer,code:String,name:String,announcement:String,status:Byte,}
        let http_request = API.getRequest(565, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改聊天室开/关闭状态
    async updateRoomStatus(params){
    //{roomId:Integer,code:String,name:String,announcement:String,status:Byte,}
        let http_request = API.getRequest(566, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //请求聊天室地址
    async selectRoomAddr(params){
    //{chatRoomId:Integer,code:String,}
        let http_request = API.getRequest(567, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ChatRoomAPI = new ChatRoom()
export default ChatRoomAPI
