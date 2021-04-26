import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewAction {
    //null
    async selectByPrimaryKey(params){
    //
        let http_request = API.getRequest(98, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewActionAPI = new ViewAction()
export default ViewActionAPI
