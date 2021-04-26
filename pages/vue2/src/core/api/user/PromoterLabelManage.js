import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class PromoterLabelManage {
    //添加标签
    async addLabel(params){
    //{id:Integer,labelName:String,type:Integer,}
        let http_request = API.getRequest(536, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改标签
    async update(params){
    //{id:Integer,labelName:String,state:Integer,}
        let http_request = API.getRequest(537, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询标签
    async queryLabelList(params){
    //{id:Integer,labelName:String,type:Integer,}
        let http_request = API.getRequest(539, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const PromoterLabelManageAPI = new PromoterLabelManage()
export default PromoterLabelManageAPI
