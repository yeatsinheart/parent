import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class GameReport {
    //注单管理
    async orderControl(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,userName:String,channelName:String,thirdOrderId:String,gameBigType:Integer,partnerId:Integer,status2:Integer,status:Integer,}
        let http_request = API.getRequest(403, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //个人统计
    async queryPersonalAll(params) {
        //{userIdBiz:Integer,begTimeMill:Long,endTimeMill:Long,}
        let http_request = API.getRequest(448, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    // 游戏记录导出
    async exportOrderControl(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,userName:String,channelName:String,thirdOrderId:String,gameBigType:Integer,partnerId:Integer,status2:Integer,status:Integer,}
        let http_request = API.getRequest(553, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const GameReportAPI = new GameReport()
export default GameReportAPI
