import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewSubGame {
    //查看子游戏视图列表
    async findViewSubGamePaging(params) {
        //{gameName:String,subgameTypeId:Integer,menuId:Integer,isShow:Boolean,subType:Integer,createTimeBeg:Long,createTimeEnd:Long,}
        let http_request = API.getRequest(148, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async findViewSubGamePagingForClient(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(149, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看子游戏视图详情
    async selectByPrimaryKey(params) {
        //{id:Integer,}
        let http_request = API.getRequest(150, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑子游戏视图
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,gameName:String,sort:Integer,img:String,isShow:Boolean,imgThumb:String,subType:Integer,deleted:Boolean,}
        let http_request = API.getRequest(151, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //显示/隐藏子游戏视图
    async updateViewSubGameIsShowById(params) {
        //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(152, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewSubGameAPI = new ViewSubGame()
export default ViewSubGameAPI
