import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';
class Merchant {
    //保存使用规则方案
    async saveDefaultConfig(params){
    //{type:Byte,configType:Byte,useRuleConfig:String,name:String,}
        let http_request = API.getRequest(268, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //编辑默认配置
    async updateDefaultConfig(params){
    //{payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,relationId:Integer,status:Byte,}
        let http_request = API.getRequest(269, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除默认规则配置
    async deleteDefaultConfig(params){
    //{payConfigId:Integer,}
        let http_request = API.getRequest(270, API.version,params,true,API.cacheTime)
        
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询站点关联的支付通道
    async findRelationWay(params){
    //{channelType:Byte,wayUsable:Byte,relationId:Integer,}
        let http_request = API.getRequest(358, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询站点还未配置过的支付方式
    async selectTypeCodeList(params){
    //{typeCodeId:Integer,selfCode:String,wayId:Integer,otherCode:String,typeDescription:String,status:Byte,}
        let http_request = API.getRequest(359, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查找支付通道的某个支付方式的配置
    async selectTypeCodeConfig(params){
    //{typeCodeId:Integer,}
        let http_request = API.getRequest(360, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //站点配置支付通道的某个支付方式
    async configMerchantWayType(params){
    //{relationId:Integer,nickname:String,typeCodeId:Integer,supportClient:String,levels:java.util.List,keyConfig:String,useRuleConfig:String,discountConfig:String,}
        let http_request = API.getRequest(361, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //站点修改支付通道的某个支付方式的配置
    async updateMerchantWayTypeConfig(params){
    //{relationTypeId:Integer,payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,levelIdList:java.util.List,supportClient:String,nickname:String,}
        let http_request = API.getRequest(362, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查找站长的开通的支付通道的某个支付方式的配置
    async selectRelationTypeConfig(params){
    //{relationTypeId:Integer,}
        let http_request = API.getRequest(363, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //管理支付方式是否启用
    async manageMerchantWayTypeUsable(params){
    //{relationTypeId:Integer,status:Byte,}
        let http_request = API.getRequest(364, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查找用户充值等级与充值通道、入款账号的关联关系
    async findRechargeLevel(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(367, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //创建用户充值等级和充值通道的关联关系
    async createRechargeLevel(params){
    //{levelId:Integer,levelName:String,accountList:java.util.List,relationTypeList:java.util.List,}
        let http_request = API.getRequest(369, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询站点使用中的通道以及充值类型
    async selectUsedWay(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(370, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除线上支付平台
    async deleteWayType(params){
    //{relationTypeId:Integer,status:Byte,}
        let http_request = API.getRequest(375, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查找通道配置
    async findWayConfig(params){
    //{typeCodeId:Integer,selfCode:String,wayId:Integer,otherCode:String,typeDescription:String,status:Byte,}
        let http_request = API.getRequest(381, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //站点-接入通道配置
    async createWayConfig(params){
    //{relationId:Integer,content:String,status:Byte,}
        let http_request = API.getRequest(382, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //管理代付通道是否可用
    async manageMerchantWayUsable(params){
    //{relationId:Integer,status:Byte,}
        let http_request = API.getRequest(393, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询站点-通道配置
    async selectMerchantWayKeyConfig(params){
    //{channelType:Byte,wayUsable:Byte,relationId:Integer,}
        let http_request = API.getRequest(401, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //修改站点-通道配置
    async updateMerchantWayKeyConfig(params){
    //{payConfigId:Integer,type:String,description:String,content:String,belong:String,createTime:LocalDateTime,createUid:Integer,createUserName:String,updateTime:LocalDateTime,updateUid:Integer,updateUserName:String,relationId:Integer,status:Byte,}
        let http_request = API.getRequest(402, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查找站点还未配置的代付通道
    async selectRemitWay(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(434, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //查询站点三方支付的常用配置
    async findDefaultConfig(params){
    //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(436, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //删除站点-通道（代付）关联关系 
    async deleteMerchantWay(params){
    //{channelType:Byte,wayUsable:Byte,relationId:Integer,}
        let http_request = API.getRequest(441, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
    //站点开通支付通道
    async createMerchantWayRelation(params){
    //{merchantId:Integer,wayId:Integer,note:String,}
        let http_request = API.getRequest(442, API.version,params,false,API.cacheTime)
        //需要传入用户信息
        if(store.state.user && store.state.user.token) {
            http_request.request.headers.t=store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}
const MerchantAPI = new Merchant()
export default MerchantAPI
