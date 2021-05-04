import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Slideshow {
    //修改轮播图
    async update(params) {
        //
        let http_request = API.getRequest(278, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const SlideshowAPI = new Slideshow()
export default SlideshowAPI
