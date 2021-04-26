import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class ViewActivity {
    //分页查询
    async findViewActivityPaging(params){
    //{title:String,createName:String,isShow:Boolean,subType:Integer,actType:Integer,createTimeBeg:String,createTimeEnd:String,langId:String,}
        let http_request = API.getRequest(100, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //null
    async findViewActivityPagingForClient(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(101, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除活动
    async deleteByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(102, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //新增活动
    async insertSelective(params){
    //{title:String,sort:Integer,imgPc:String,content:String,isShow:Boolean,subType:Integer,actType:Integer,des:String,remarks:String,imgPcThumb:String,langId:String,jumpType:Integer,begTime:Long,endTime:Long,showClient:String,levelList:String,vipLevelList:String,actStyle:Integer,payStyle:Integer,imgMobile:String,imgMobileThumb:String,isLevelLimit:Boolean,isVipLimit:Boolean,isAgentLimit:Boolean,agentList:String,isGameLimit:Boolean,gameList:String,parternerList:String,isTodayReg:Boolean,isOnceDayIp:Boolean,isTodayCharge:Boolean,isMobileBind:Boolean,isOneAccountOneDay:Boolean,isOneAccount:Boolean,totalAndBenifitList:java.util.List,times:BigDecimal,totalAndBenifitList:java.util.List,isChargeCount:Boolean,isBenifitCount:Boolean,times:BigDecimal,amount:BigDecimal,times:BigDecimal,amount:BigDecimal,times:BigDecimal,}
        let http_request = API.getRequest(103, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //批量删除活动
    async batchDeleteByIds(params){
    //
        let http_request = API.getRequest(104, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查看活动详情
    async selectByPrimaryKey(params){
    //{id:Integer,}
        let http_request = API.getRequest(105, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑活动
    async updateByPrimaryKeySelective(params){
    //{id:Integer,title:String,sort:Integer,imgPc:String,content:String,isShow:Boolean,subType:Integer,des:String,remarks:String,imgPcThumb:String,langId:String,begTime:Long,endTime:Long,showClient:String,levelList:String,vipLevelList:String,imgMobile:String,imgMobileThumb:String,isLevelLimit:Boolean,isVipLimit:Boolean,isAgentLimit:Boolean,agentList:String,isGameLimit:Boolean,gameList:String,parternerList:String,isTodayReg:Boolean,isOnceDayIp:Boolean,isTodayCharge:Boolean,isMobileBind:Boolean,isOneAccountOneDay:Boolean,isOneAccount:Boolean,totalAndBenifitList:java.util.List,times:BigDecimal,totalAndBenifitList:java.util.List,isChargeCount:Boolean,isBenifitCount:Boolean,times:BigDecimal,amount:BigDecimal,times:BigDecimal,amount:BigDecimal,times:BigDecimal,}
        let http_request = API.getRequest(106, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //显示隐藏活动
    async updateViewActivityIsShowById(params){
    //{id:Integer,isShow:Boolean,}
        let http_request = API.getRequest(107, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const ViewActivityAPI = new ViewActivity()
export default ViewActivityAPI
