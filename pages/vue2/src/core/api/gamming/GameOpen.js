import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class GameOpen {
    //游戏基本信息修改
    async editGame(params) {
        //{name:String,icon:String,isManage:Integer,id:Integer,tenantId:Integer,partnerId:Integer,channelId:Integer,gameId:Integer,content:String,status:Integer,gammingTypeId:Integer,typeId:Integer,theme:String,maintenance:String,channelGameCode:String,sequence:Integer,}
        let http_request = API.getRequest(477, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改游戏排序
    async editGameSequence(params) {
        //{name:String,icon:String,isManage:Integer,id:Integer,tenantId:Integer,partnerId:Integer,channelId:Integer,gameId:Integer,content:String,status:Integer,gammingTypeId:Integer,typeId:Integer,theme:String,maintenance:String,channelGameCode:String,sequence:Integer,}
        let http_request = API.getRequest(638, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑热门
    async editHot(params) {
        //{action:String,hots:java.util.List,}
        let http_request = API.getRequest(654, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑渠道
    async editChannel(params) {
        //{name:String,icon:String,canBack:Integer,channelId:Integer,content:String,createTime:LocalDateTime,gameLoby:String,gammingTypeId:Integer,id:Integer,isMain:Integer,maintenance:String,partnerId:Integer,sequence:Integer,status:Integer,tax:Integer,tenantId:Integer,type:Integer,updateTime:LocalDateTime,walletType:Integer,webRedirectType:Integer,}
        let http_request = API.getRequest(655, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑游戏大类
    async editGamming(params) {
        //{name:String,icon:String,gammingTypeId:Integer,id:Integer,sequence:Integer,tenantId:Integer,}
        let http_request = API.getRequest(656, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const GameOpenAPI = new GameOpen()
export default GameOpenAPI
