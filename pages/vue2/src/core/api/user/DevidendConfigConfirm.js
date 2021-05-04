import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class DevidendConfigConfirm {
    //给下级代理签约分红
    async addDevidendConfigConfirm(params) {
        //{downUserId:Integer,devidendRuleConfig:java.util.List,}
        let http_request = API.getRequest(394, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台，代理查询自己的分红配置
    async querySelfDevideConfig(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(395, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台，代理查询自己的下级代理分红配置
    async queryDownDevideConfig(params) {
        //{downUserId:Integer,downUserName:String,state:Integer,}
        let http_request = API.getRequest(397, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台，代理重新签约下级分红配置
    async updateDevidendConfigCirm(params) {
        //{downUserId:Integer,devidendRuleConfig:java.util.List,}
        let http_request = API.getRequest(398, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台，代理取消下级分红配置
    async cancelDownDevideConfig(params) {
        //{downUserId:Integer,downUserName:String,state:Integer,}
        let http_request = API.getRequest(399, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //同意或者拒绝分红状态
    async agreeOrResultConfig(params) {
        //{downUserId:Integer,downUserName:String,state:Integer,}
        let http_request = API.getRequest(471, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询下级代理的分红设置
    async queryAllDownDevidendCOnfigConfirm(params) {
        //{downUserId:Integer,downUserName:String,state:Integer,}
        let http_request = API.getRequest(474, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台，代理查询自己的分红配置包含所有游戏
    async querySelfDevideConfigContain(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(549, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const DevidendConfigConfirmAPI = new DevidendConfigConfirm()
export default DevidendConfigConfirmAPI
