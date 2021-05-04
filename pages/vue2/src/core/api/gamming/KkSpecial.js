import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class KkSpecial {
    //查询应用开通的所有kk彩票
    async queryLotteryOldApi(params) {
        //
        let http_request = API.getRequest(476, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //开售/停售彩种
    async statusLottery(params) {
        //{name:String,icon:String,isManage:Integer,id:Integer,tenantId:Integer,partnerId:Integer,channelId:Integer,gameId:Integer,content:String,status:Integer,gammingTypeId:Integer,typeId:Integer,theme:String,maintenance:String,channelGameCode:String,sequence:Integer,}
        let http_request = API.getRequest(480, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询玩法组
    async ticketGroup(params) {
        //{api:String,platformLotteryId:Long,platformId:Long,lotteryTypeId:Integer,includeDisable:boolean,pageNo:Integer,pageSize:Integer,}
        let http_request = API.getRequest(481, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询玩法项
    async ticketDetail(params) {
        //{api:String,id:Integer,platformLotteryId:Long,platformId:Long,lotteryTypeId:Integer,methodCrowdId:Long,methodGroupId:Long,includeDisable:boolean,pageNo:Integer,pageSize:Integer,}
        let http_request = API.getRequest(482, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //开启关闭玩法项
    async updateTicketDetail(params) {
        //{api:String,status:Integer,id:Long,}
        let http_request = API.getRequest(483, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询历史开奖记录
    async historyLottery(params) {
        //{api:String,platformId:Long,lotteryId:Integer,pageNo:Integer,pageSize:Integer,}
        let http_request = API.getRequest(484, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //根据tenantGameID查找kk游戏
    async queryLottery(params) {
        //
        let http_request = API.getRequest(624, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取所有彩票开奖结果
    async lastLotteryResult(params) {
        //{api:String,platformId:Long,lotteryId:Long,pageNo:Integer,pageSize:Integer,}
        let http_request = API.getRequest(631, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const KkSpecialAPI = new KkSpecial()
export default KkSpecialAPI
