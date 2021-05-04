import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class PlayProxy {
    //登录游戏
    async login(params) {
        //{gameId:Integer,channelEntranceGameId:Integer,enterChannel:Integer,}
        let http_request = API.getRequest(478, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const PlayProxyAPI = new PlayProxy()
export default PlayProxyAPI
