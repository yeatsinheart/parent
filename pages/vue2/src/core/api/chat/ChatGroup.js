import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ChatGroup {
    //创建群组
    async createChatGroup(params){
    //{code:String,name:String,inviteAccids:java.util.List,announcement:String,intro:String,icon:String,msg:String,joinmode:Integer,magree:Integer,beinvitemode:Integer,invitemode:Integer,uptinfomode:Integer,}
        let http_request = API.getRequest(576, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //解散群组
    async deleteChatGroup(params){
    //{groupId:Integer,code:String,}
        let http_request = API.getRequest(577, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //拉人入群
    async invite(params){
    //{groupId:Integer,code:String,inviteAccids:java.util.List,msg:String,magree:Integer,}
        let http_request = API.getRequest(578, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //踢人出群
    async kick(params){
    //{groupId:Integer,code:String,inviteAccids:java.util.List,msg:String,magree:Integer,}
        let http_request = API.getRequest(579, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改群信息
    async updateGroup(params){
    //{groupId:Integer,code:String,name:String,announcement:String,intro:String,icon:String,joinmode:Integer,beinvitemode:Integer,invitemode:Integer,uptinfomode:Integer,}
        let http_request = API.getRequest(580, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取群信息
    async getGroupInfo(params){
    //{groupId:Integer,groupIdList:java.util.List,code:String,ope:Integer,}
        let http_request = API.getRequest(581, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改群成员昵称
    async updateMemberInfo(params){
    //{groupId:Integer,code:String,memberId:Integer,nick:String,}
        let http_request = API.getRequest(582, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取群成员信息
    async getMembersInfo(params){
    //{groupId:Integer,code:String,timetag:String,limit:Integer,reverse:Integer,}
        let http_request = API.getRequest(583, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //成员退群
    async leaveGroup(params){
    //{groupId:Integer,code:String,}
        let http_request = API.getRequest(584, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //移交群主
    async changeOwner(params){
    //{groupId:Integer,code:String,chatUserId:Integer,leave:Integer,}
        let http_request = API.getRequest(585, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //添加管理员
    async addManager(params){
    //{groupId:Integer,code:String,managerAccids:java.util.List,}
        let http_request = API.getRequest(586, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //解除管理员
    async removeManager(params){
    //{groupId:Integer,code:String,managerAccids:java.util.List,}
        let http_request = API.getRequest(587, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //禁言群
    async disableSpeak(params){
    //{groupId:Integer,code:String,muteType:Integer,}
        let http_request = API.getRequest(588, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //禁言群成员
    async disableSpeakMembers(params){
    //{groupId:Integer,code:String,chatUserId:Integer,mute:Integer,}
        let http_request = API.getRequest(589, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询群组详细信息
    async queryDetail(params){
    //{groupId:Integer,groupIdList:java.util.List,code:String,ope:Integer,}
        let http_request = API.getRequest(597, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改群修改消息提醒开关
    async muteTeam(params){
    //{groupId:Integer,code:String,ope:Integer,}
        let http_request = API.getRequest(598, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ChatGroupAPI = new ChatGroup()
export default ChatGroupAPI
