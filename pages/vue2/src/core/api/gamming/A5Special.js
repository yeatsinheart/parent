import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class A5Special {
    //获取A5后台菜单
    async backend(params) {
        //{api:String,functionid:String,account:String,password:String,isSSL:String,}
        let http_request = API.getRequest(625, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const A5SpecialAPI = new A5Special()
export default A5SpecialAPI
