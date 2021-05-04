import API from '@/core/api/API'
import {post} from '@/axios/request'

class AppDomain {
    //获取域名列表
    async findAppDomainStrPaging(params) {
        //{id:Integer,appIdBiz:Integer,domainBiz:String,remark:String,createTime:LocalDateTime,createUid:Integer,updateTime:LocalDateTime,updateUid:Integer,deleted:Boolean,version:Integer,}
        let http_request = API.getRequest(486, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const AppDomainAPI = new AppDomain()
export default AppDomainAPI
