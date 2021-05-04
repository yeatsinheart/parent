import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class CommonGroupMember {
    //修改用户的入款层级
    async updateRechargeMemberRelation(params) {
        //{bUserId:Integer,oldGroupId:Integer,newGroupId:Integer,}
        let http_request = API.getRequest(353, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const CommonGroupMemberAPI = new CommonGroupMember()
export default CommonGroupMemberAPI
