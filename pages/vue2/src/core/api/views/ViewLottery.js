import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewLottery {
    //null
    async findViewLotteryPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(252, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async findViewHotLotteryPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(253, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取游戏列表
    async getGameList(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(470, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //分页查看游戏记录
    async findViewLotteryPaging(params){
    //{id:Integer,lotteryName:String,isHot:Boolean,isQuick:Boolean,isNew:Boolean,typeCode:String,gameType:String,lotteryCode:String,createTime:LocalDateTime,createUid:Integer,updateTime:LocalDateTime,updateUid:Integer,deleted:Boolean,version:Integer,gamebigtype:Integer,isrepair:Boolean,isShow:Boolean,}
        let http_request = API.getRequest(619, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑游戏视图（图片）
    async updatePic(params){
    //{lotteryName:String,isHot:Boolean,isShow:Boolean,lotteryCode:String,img:String,isrepair:Boolean,repairbeg:String,repairend:String,}
        let http_request = API.getRequest(620, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑游戏视图（显示）
    async updateIsShow(params){
    //{lotteryName:String,isHot:Boolean,isShow:Boolean,lotteryCode:String,img:String,isrepair:Boolean,repairbeg:String,repairend:String,}
        let http_request = API.getRequest(621, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑游戏视图（热门）
    async updateIsHot(params){
    //{lotteryName:String,isHot:Boolean,isShow:Boolean,lotteryCode:String,img:String,isrepair:Boolean,repairbeg:String,repairend:String,}
        let http_request = API.getRequest(622, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑游戏视图（维护）
    async updateIsRepair(params){
    //{lotteryName:String,isHot:Boolean,isShow:Boolean,lotteryCode:String,img:String,isrepair:Boolean,repairbeg:String,repairend:String,}
        let http_request = API.getRequest(623, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取游戏列表带厂家
    async getGameListForParterner(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(632, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //获取游戏大类
    async selectGameBigTypeForSelect(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(633, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewLotteryAPI = new ViewLottery()
export default ViewLotteryAPI
