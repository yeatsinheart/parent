import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Partner {
    //kk杀率黑名单配置
    async addKill(params) {
        //
        let http_request = API.getRequest(479, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //根据游戏角色信息查找实际用户
    async getUserAccount(params) {
        //{account:String,accountId:Long,accountPw:String,autoTransfer:Integer,channelId:Integer,content:String,createTime:LocalDateTime,currency:String,id:Integer,partnerId:Integer,tenantId:Integer,updateTime:LocalDateTime,userId:Integer,}
        let http_request = API.getRequest(485, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取站点开通的所有游戏大类
    async getTenantChannelGammingType(params) {
        //{name:String,icon:String,canBack:Integer,channelId:Integer,content:String,createTime:LocalDateTime,gameLoby:String,gammingTypeId:Integer,id:Integer,isMain:Integer,maintenance:String,partnerId:Integer,sequence:Integer,status:Integer,tax:Integer,tenantId:Integer,type:Integer,updateTime:LocalDateTime,walletType:Integer,webRedirectType:Integer,}
        let http_request = API.getRequest(629, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取游戏所属游戏大类
    async getTenantGameGammingType(params) {
        //{name:String,icon:String,isManage:Integer,id:Integer,tenantId:Integer,partnerId:Integer,channelId:Integer,gameId:Integer,content:String,status:Integer,gammingTypeId:Integer,typeId:Integer,theme:String,maintenance:String,channelGameCode:String,sequence:Integer,}
        let http_request = API.getRequest(630, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取所有整合的游戏数据
    async partnerInfoOfTenant(params) {
        //{tenantId:Integer,}
        let http_request = API.getRequest(635, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //关闭租户渠道显示
    async statusChangeTenantChannel(params) {
        //{name:String,icon:String,canBack:Integer,channelId:Integer,content:String,createTime:LocalDateTime,gameLoby:String,gammingTypeId:Integer,id:Integer,isMain:Integer,maintenance:String,partnerId:Integer,sequence:Integer,status:Integer,tax:Integer,tenantId:Integer,type:Integer,updateTime:LocalDateTime,walletType:Integer,webRedirectType:Integer,}
        let http_request = API.getRequest(636, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //关闭租户游戏显示
    async statusChangeTenantGame(params) {
        //{name:String,icon:String,isManage:Integer,id:Integer,tenantId:Integer,partnerId:Integer,channelId:Integer,gameId:Integer,content:String,status:Integer,gammingTypeId:Integer,typeId:Integer,theme:String,maintenance:String,channelGameCode:String,sequence:Integer,}
        let http_request = API.getRequest(637, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取热门
    async hotsOfTenant(params) {
        //{tenantId:Integer,}
        let http_request = API.getRequest(653, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const PartnerAPI = new Partner()
export default PartnerAPI
