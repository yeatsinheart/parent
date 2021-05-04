import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class UserBankCard {
    //添加银行卡
    async addBankCard(params) {
        //{bankCardId:Integer,bankCardNum:String,createTime:LocalDateTime,state:String,cardHolder:String,bankBranch:String,createUserId:Integer,operateTime:LocalDateTime,operateUserId:Integer,bankCode:String,createUserName:String,userName:String,bankName:String,province:String,city:String,icon:String,bUserId:Integer,}
        let http_request = API.getRequest(189, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //根据用户查询银行卡
    async queryBankCardByUserId(params) {
        //{bankCardId:Integer,bankCardNum:String,createTime:LocalDateTime,state:String,cardHolder:String,bankBranch:String,createUserId:Integer,operateTime:LocalDateTime,operateUserId:Integer,bankCode:String,createUserName:String,userName:String,bankName:String,province:String,city:String,icon:String,bUserId:Integer,}
        let http_request = API.getRequest(190, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //根据银行卡id查询银行卡
    async queryBankCardById(params) {
        //{id:Integer,isLike:Boolean,userName:String,bankCardId:Integer,bankCardNum:String,bankType:String,userId:Integer,state:Integer,cardHolder:String,bankCode:String,firstResult:Integer,}
        let http_request = API.getRequest(191, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取银行卡列表
    async queryBankCardList(params) {
        //{id:Integer,isLike:Boolean,userName:String,bankCardId:Integer,bankCardNum:String,bankType:String,userId:Integer,state:Integer,cardHolder:String,bankCode:String,firstResult:Integer,}
        let http_request = API.getRequest(275, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //解绑银行卡
    async deleteBankCardById(params) {
        //{id:Integer,isLike:Boolean,userName:String,bankCardId:Integer,bankCardNum:String,bankType:String,userId:Integer,state:Integer,cardHolder:String,bankCode:String,firstResult:Integer,}
        let http_request = API.getRequest(355, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //银行卡通过审核
    async checkBankCard(params) {
        //{id:Integer,isLike:Boolean,userName:String,bankCardId:Integer,bankCardNum:String,bankType:String,userId:Integer,state:Integer,cardHolder:String,bankCode:String,firstResult:Integer,}
        let http_request = API.getRequest(356, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //提现)查询用户的银行卡
    async queryDrawBankCardByUserId(params) {
        //{bankCardId:Integer,bankCardNum:String,createTime:LocalDateTime,state:String,cardHolder:String,bankBranch:String,createUserId:Integer,operateTime:LocalDateTime,operateUserId:Integer,bankCode:String,createUserName:String,userName:String,bankName:String,province:String,city:String,icon:String,bUserId:Integer,}
        let http_request = API.getRequest(400, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const UserBankCardAPI = new UserBankCard()
export default UserBankCardAPI
