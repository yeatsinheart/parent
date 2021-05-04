import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Cost {
    //添加成本定义
    async addCost(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(639, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改成本定义
    async updateCost(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(640, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除成本定义
    async deleteCost(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(641, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询成本定义
    async queryCost(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(642, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询成本定义详情
    async queryCostDetail(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(643, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //玩家查询自己所在代理线是否开启了全民代理
    async isInAllProxy(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(644, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询可参与成本的一级代理id
    async selectProxyForSelect(params) {
        //{id:Integer,name:String,type:String,state:Integer,oneJsonConfig:String,twoJsonConfig:String,threeJsonConfig:String,gameIds:java.util.List,parternerIds:java.util.List,proxyGroupIds:java.util.List,vipLevelIds:java.util.List,rechargeLevelIds:java.util.List,}
        let http_request = API.getRequest(648, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const CostAPI = new Cost()
export default CostAPI
