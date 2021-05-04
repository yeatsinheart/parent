import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class TeamCountByUserReport {
    //团队报表
    async getTeamTotal(params) {
        //{begTimeMill:String,endTimeMill:String,orderId:String,accountChangeTypeList:java.util.List,gameIdList:java.util.List,status:Integer,status2:Integer,parternerId:Integer,gameBigType:Integer,userName:String,isContains:Integer,groupByType:Integer,}
        let http_request = API.getRequest(455, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    // 团队报表PC版本
    async totalWinlose(params) {
        //{begTimeMill:String,endTimeMill:String,userName:String,isShowZero:Integer,gameBigType:Integer,}
        let http_request = API.getRequest(554, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const TeamCountByUserReportAPI = new TeamCountByUserReport()
export default TeamCountByUserReportAPI
