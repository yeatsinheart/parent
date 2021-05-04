import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class Salary {
    //查询下级日工资的签约情况
    async queryDownSalary(params) {
        //{userName:String,state:Short,bUserId:Integer,}
        let http_request = API.getRequest(462, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询自己的日工资配置
    async queryMeSalary(params) {
        //{userName:String,state:Short,bUserId:Integer,}
        let http_request = API.getRequest(463, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //对自己的日工资操作
    async meSalaryAction(params) {
        //{id:Integer,state:Short,}
        let http_request = API.getRequest(464, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //对下级日工资的操作
    async downSalaryAction(params) {
        //{id:Integer,actionName:String,twoJsonConfig:String,threeJsonConfig:String,}
        let http_request = API.getRequest(465, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const SalaryAPI = new Salary()
export default SalaryAPI
