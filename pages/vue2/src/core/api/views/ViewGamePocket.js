import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewGamePocket {
    //分页查询
    async findViewGamePocketPaging(params) {
        //{name:String,isShow:Boolean,subType:Integer,createTimeBeg:Long,createTimeEnd:Long,}
        let http_request = API.getRequest(134, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async findViewGamePocketPagingForClient(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(135, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看游戏钱包
    async selectByPrimaryKey(params) {
        //{id:Integer,}
        let http_request = API.getRequest(136, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //编辑游戏钱包
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,name:String,sort:Integer,img:String,isShow:Boolean,subType:Integer,imgThumb:String,status:Integer,deleted:Boolean,}
        let http_request = API.getRequest(137, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //显示/隐藏游戏钱包
    async updateViewGamePocketIsShowById(params) {
        //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(138, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewGamePocketAPI = new ViewGamePocket()
export default ViewGamePocketAPI
