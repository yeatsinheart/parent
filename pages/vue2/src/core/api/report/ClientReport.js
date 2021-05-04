import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ClientReport {
    //游戏记录
    async queryByPage(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(338, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //个人总览
    async getPersonal(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(365, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //团队总览
    async getTeamTotal(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(366, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //账变下拉
    async selectAccountChangeForSelect(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(368, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //团队游戏数据
    async queryTeamGameByPage(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(377, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //个人账变记录
    async getMoneyRecordByPage(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(378, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //团队账变记录
    async getMoneyTeamRecordByPage(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(379, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //中奖轮播
    async recentWin(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(388, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //昨日中奖榜
    async lastDayWin(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(391, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //昨日盈利榜
    async lastDayWinLose(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(392, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //追号记录
    async queryPreBetByPage(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,gameIdList:java.util.List,status:Integer,userName:String,isContains:Integer,}
        let http_request = API.getRequest(407, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //追号详情
    async queryPreBetByOrderId(params) {
        //{orderId:String,prebetTime:Long,}
        let http_request = API.getRequest(408, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //团队追号记录
    async queryTeamPreBetByPage(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,gameIdList:java.util.List,status:Integer,userName:String,isContains:Integer,}
        let http_request = API.getRequest(419, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ClientReportAPI = new ClientReport()
export default ClientReportAPI
