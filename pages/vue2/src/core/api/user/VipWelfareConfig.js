import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class VipWelfareConfig {
    //添加VIP福利配置
    async addVipWelfareConfig(params) {
        //
        let http_request = API.getRequest(201, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除VIP福利配置
    async deleteVipWelfareConfig(params) {
        //
        let http_request = API.getRequest(202, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询VIP福利配置
    async queryVipWelfareConfig(params) {
        //
        let http_request = API.getRequest(203, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改VIP福利配置
    async updateVipWelfareConfig(params) {
        //
        let http_request = API.getRequest(204, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const VipWelfareConfigAPI = new VipWelfareConfig()
export default VipWelfareConfigAPI
