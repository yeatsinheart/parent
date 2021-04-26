import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewGameMenu {
    //分页查询
    async findViewGameMenuPaging(params){
    //{name:String,gameGroup:Integer,isShow:Boolean,subType:Integer,createTimeBeg:Long,createTimeEnd:Long,}
        let http_request = API.getRequest(128, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async findViewGameMenuPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(129, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看游戏菜单
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(130, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增游戏菜单
    async updateByPrimaryKeySelective(params){
    //{id:Integer,name:String,sort:Integer,img:String,isShow:Boolean,desciption:String,gameGroup:Integer,gameId:Integer,imgThumb:String,subType:Integer,deleted:Boolean,}
        let http_request = API.getRequest(131, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //显示/隐藏游戏菜单
    async updateViewGameMenuIsShowById(params){
    //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(132, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增游戏菜单
    async selectAllForSelect(params){
    //{isShow:Boolean,ownerId:Integer,appId:Integer,langIdList:java.util.List,status:Integer,}
        let http_request = API.getRequest(133, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewGameMenuAPI = new ViewGameMenu()
export default ViewGameMenuAPI
