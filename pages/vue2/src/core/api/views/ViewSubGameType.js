import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewSubGameType {
    //查看子游戏类型视图列表
    async findViewSubGameTypePaging(params) {
        //{id:Integer,typeName:String,menuId:Integer,createTimeBeg:Long,createTimeEnd:Long,isShow:Boolean,}
        let http_request = API.getRequest(153, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async findViewSubGameTypePagingForClient(params) {
        //{menuId:Integer,}
        let http_request = API.getRequest(154, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看子游戏类型视图详情
    async selectByPrimaryKey(params) {
        //{id:Integer,}
        let http_request = API.getRequest(155, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑子游戏类型视图
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,typeName:String,sort:Integer,img:String,imgThumb:String,deleted:Boolean,isShow:Boolean,}
        let http_request = API.getRequest(156, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //显示/隐藏子游戏类型视图
    async updateViewSubGameTypeIsShowById(params) {
        //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(157, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async selectAllForSelect(params) {
        //{menuId:Integer,isShow:Boolean,ownerId:Integer,appId:Integer,langIdList:java.util.List,}
        let http_request = API.getRequest(158, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewSubGameTypeAPI = new ViewSubGameType()
export default ViewSubGameTypeAPI
