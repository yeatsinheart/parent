import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class Introducer {
    //添加 介绍人，增员人
    async addIntroducer(params){
    //{id:Integer,type:Integer,name:String,createFee:Double,maintainFee:Double,firstMonths:Double,secondMonths:Double,thirdMonths:Double,fourthMonths:Double,fifthMonths:Double,sixtotweMonths:Double,secondYears:Double,thirdYears:Double,state:Integer,}
        let http_request = API.getRequest(529, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改 介绍人，增员人
    async updateIntroducer(params){
    //{id:Integer,type:Integer,name:String,createFee:Double,maintainFee:Double,firstMonths:Double,secondMonths:Double,thirdMonths:Double,fourthMonths:Double,fifthMonths:Double,sixtotweMonths:Double,secondYears:Double,thirdYears:Double,state:Integer,}
        let http_request = API.getRequest(531, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询集合 介绍人，增员人
    async queryIntroducerList(params){
    //{type:Integer,state:Integer,}
        let http_request = API.getRequest(532, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询集合 介绍人，增员人
    async queryIntroducerListForSelect(params){
    //{type:Integer,state:Integer,}
        let http_request = API.getRequest(555, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const IntroducerAPI = new Introducer()
export default IntroducerAPI
