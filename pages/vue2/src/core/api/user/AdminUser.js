import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class AdminUser {
    //后台用户修改前台用户信息
    async backUserUpdateFrontUserInfo(params) {
        //{id:Integer,phone:String,email:String,wechat:String,qq:String,state:Integer,nickName:String,actualName:String,remark:String,isProxy:Boolean,bonus:java.util.List,}
        let http_request = API.getRequest(188, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站长-用户注册
    async webMasterRegister(params) {
        //{id:Integer,bUserId:Integer,bUserName:String,roleId:Integer,roleName:String,password:String,phone:String,}
        let http_request = API.getRequest(256, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询后台用户列表
    async queryWebMasterUserList(params) {
        //{id:Integer,bUserId:Integer,bUserName:String,roleId:Integer,roleName:String,password:String,phone:String,}
        let http_request = API.getRequest(261, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询会员用户列表
    async queryFrontUserList(params) {
        //{userName:String,startTime:String,endTime:String,startMoney:String,endMoney:String,state:Integer,pUserName:String,fuzzy:Boolean,}
        let http_request = API.getRequest(263, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //删除管理账号
    async deleteWebMaster(params) {
        //{id:Integer,bUserId:Integer,bUserName:String,roleId:Integer,roleName:String,password:String,phone:String,}
        let http_request = API.getRequest(288, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站长修改会员的密码，交易密码
    async resetPwd(params) {
        //{bUserId:Integer,pwd:String,type:String,}
        let http_request = API.getRequest(354, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站长登录
    async login(params) {
        //{userName:String,password:String,verifyCode:String,}
        let http_request = API.getRequest(357, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //站长-注册前台用户
    async adminRegistWebUser(params) {
        //{id:Integer,userName:String,password:String,isProxy:Boolean,tradePwd:String,phone:String,emaill:String,qq:String,wechat:String,registTime:String,currency:String,idcardName:String,state:Integer,pUserName:String,bonus:java.util.List,inviteCode:String,verifyCode:String,}
        let http_request = API.getRequest(374, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户取款审核,查看用户信息
    async queryUserForWithdrawDetails(params) {
        //{id:Integer,isLike:Boolean,userName:String,bankCardId:Integer,bankCardNum:String,bankType:String,userId:Integer,state:Integer,cardHolder:String,bankCode:String,firstResult:Integer,}
        let http_request = API.getRequest(387, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取当前站长可分配的奖金组
    async querySystemBonusList(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(390, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台用户修改自己的资料
    async updateUserInfo(params) {
        //{id:Integer,phone:String,email:String,wechat:String,qq:String,state:Integer,nickName:String,actualName:String,remark:String,isProxy:Boolean,bonus:java.util.List,}
        let http_request = API.getRequest(437, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台用户修改自己的密码
    async updatePwd(params) {
        //{pwd:String,npwd:String,tradePwd:String,ntradePwd:String,}
        let http_request = API.getRequest(440, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台用户获取自己的用户信息
    async backUserGetSelfInfo(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(454, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台用户获取前台用户的充值取款情况
    async backUserGetFrontUserAccountMsg(params) {
        //{bUserId:Integer,}
        let http_request = API.getRequest(466, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //后台用户修改其他后台用户的资料
    async updateOtherUserInfo(params) {
        //{id:Integer,phone:String,email:String,wechat:String,qq:String,state:Integer,nickName:String,actualName:String,remark:String,isProxy:Boolean,bonus:java.util.List,}
        let http_request = API.getRequest(502, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //会员升级成代理
    async upgradeToProxy(params) {
        //{bUserId:Integer,}
        let http_request = API.getRequest(548, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取当前平台某个代理可分配的奖金组
    async queryProxyBonusList(params) {
        //{proxyName:String,}
        let http_request = API.getRequest(626, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const AdminUserAPI = new AdminUser()
export default AdminUserAPI
