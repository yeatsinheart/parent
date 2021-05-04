import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class SystemReport {
    //站台盈亏报表
    async appWinlose(params) {
        //{begTimeMill:String,endTimeMill:String,platformAccount:String,platformName:String,gameMaker:String,}
        let http_request = API.getRequest(517, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站台对账报表
    async appOrder(params) {
        //{begTimeMill:String,endTimeMill:String,platformAccount:String,platformName:String,gameMaker:String,}
        let http_request = API.getRequest(518, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //厂商盈亏报表
    async gameWinlose(params) {
        //{begTimeMill:String,endTimeMill:String,platformAccount:String,platformName:String,gameMaker:String,}
        let http_request = API.getRequest(519, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    // 厂商对账报表
    async gameOrder(params) {
        //{begTimeMill:String,endTimeMill:String,platformAccount:String,platformName:String,gameMaker:String,}
        let http_request = API.getRequest(520, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const SystemReportAPI = new SystemReport()
export default SystemReportAPI
