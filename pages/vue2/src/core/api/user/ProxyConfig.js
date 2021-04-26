import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ProxyConfig {
    //查询前端代理目录显示配置
    async queryProxyConfig(params){
    //{}
        let http_request = API.getRequest(506, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ProxyConfigAPI = new ProxyConfig()
export default ProxyConfigAPI
