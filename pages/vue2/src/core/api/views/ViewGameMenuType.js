import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class ViewGameMenuType {
    //分页查询
    async findViewGameMenuTypePaging(params) {
        //{id:Integer,name:String,sort:Integer,img:String,isShow:Boolean,url:String,imgThumb:String,subType:Integer,ownerId:Integer,appIdBiz:Integer,langId:String,jumpType:Integer,copyId:Integer,status:Integer,desciption:String,createTime:LocalDateTime,createUid:Integer,updateTime:LocalDateTime,updateUid:Integer,deleted:Boolean,version:Integer,}
        let http_request = API.getRequest(124, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //null
    async findViewGameMenuTypePagingForClient(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(125, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看菜单分类
    async selectByPrimaryKey(params) {
        //{id:Integer,}
        let http_request = API.getRequest(126, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //新增轮播图
    async updateByPrimaryKeySelective(params) {
        //{id:Integer,name:String,sort:Integer,img:String,isShow:Boolean,imgThumb:String,subType:Integer,desciption:String,}
        let http_request = API.getRequest(127, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const ViewGameMenuTypeAPI = new ViewGameMenuType()
export default ViewGameMenuTypeAPI
