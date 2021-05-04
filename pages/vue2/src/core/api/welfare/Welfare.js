import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Welfare {
    //参与活动
    async getWelfare(params) {
        //{activityId:Integer,}
        let http_request = API.getRequest(332, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const WelfareAPI = new Welfare()
export default WelfareAPI
