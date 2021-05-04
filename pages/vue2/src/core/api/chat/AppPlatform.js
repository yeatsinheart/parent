import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class AppPlatform {
    //创建站点-聊天平台关联关系
    async createAppPlatformRelation(params) {
        //{platformId:Integer,siteId:Integer,}
        let http_request = API.getRequest(558, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询站点-聊天平台关联关系
    async selectAppPlatform(params) {
        //{}
        let http_request = API.getRequest(559, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站点-聊天平台配置
    async configAppPlatform(params) {
        //{relationId:Integer,content:String,}
        let http_request = API.getRequest(560, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除站点-聊天平台关联关系
    async deleteAppPlatformRelation(params) {
        //{relationId:Integer,}
        let http_request = API.getRequest(561, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询站点-聊天平台配置
    async selectAppPlatformConfig(params) {
        //{relationId:Integer,}
        let http_request = API.getRequest(573, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改站点-聊天平台配置
    async updateAppPlatformConfig(params) {
        //{chatConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,}
        let http_request = API.getRequest(574, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const AppPlatformAPI = new AppPlatform()
export default AppPlatformAPI
