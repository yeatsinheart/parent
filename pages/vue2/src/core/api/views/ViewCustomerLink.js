import API from '@/core/api/API'
import {post} from '@/axios/request'

class ViewCustomerLink {
    //null
    async batchInsertSelective(params) {
        //{addViewCustomerLinkDtoList:java.util.List,linkType:Integer,}
        let http_request = API.getRequest(162, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async getAllViewCustomerLinkFront(params) {
        //{type:Integer,}
        let http_request = API.getRequest(163, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看三方客服（后台使用）
    async getAllViewCustomerLink(params) {
        //{type:Integer,}
        let http_request = API.getRequest(504, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewCustomerLinkAPI = new ViewCustomerLink()
export default ViewCustomerLinkAPI
