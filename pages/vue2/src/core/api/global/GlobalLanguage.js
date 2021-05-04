import API from '@/core/api/API'
import {post} from '@/axios/request'

class GlobalLanguage {
    //获取语言下拉
    async selectAllForSelect(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(516, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const GlobalLanguageAPI = new GlobalLanguage()
export default GlobalLanguageAPI
