import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class GlobalPicDomain {
    //获取域名地址
    async getPicDomainForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(610, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const GlobalPicDomainAPI = new GlobalPicDomain()
export default GlobalPicDomainAPI
