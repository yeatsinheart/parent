import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewCustomerThird {
    //新增三方客服
    async insertSelective(params) {
        //{id:Integer,type:Integer,url:String,secret:String,isShow:Boolean,}
        let http_request = API.getRequest(164, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看三方客服
    async selectOneForClient(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(165, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看三方客服(后台使用)
    async selectOne(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(505, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewCustomerThirdAPI = new ViewCustomerThird()
export default ViewCustomerThirdAPI
