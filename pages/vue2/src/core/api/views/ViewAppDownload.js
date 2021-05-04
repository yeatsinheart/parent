import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewAppDownload {
    //null
    async batchInsertOrUpdateSelective(params) {
        //{addViewAppDownloadDtoList:java.util.List,}
        let http_request = API.getRequest(160, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async getViewAppDownload(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(161, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewAppDownloadAPI = new ViewAppDownload()
export default ViewAppDownloadAPI
