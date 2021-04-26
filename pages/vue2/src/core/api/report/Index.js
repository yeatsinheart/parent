import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class Index {
    //按天创建ES索引index
    async createIndex(params){
    //{}
        let http_request = API.getRequest(329, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const IndexAPI = new Index()
export default IndexAPI
