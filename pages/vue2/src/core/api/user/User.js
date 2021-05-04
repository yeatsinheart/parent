import API from '@/core/api/API'
import {post} from '@/axios/request'
import store from 'store';

class User {
    //用户登录
    async login(params) {
        //{userName:String,password:String,verifyCode:String,}
        let http_request = API.getRequest(166, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台-用户注册
    async frontRegister(params) {
        //{id:Integer,userName:String,password:String,isProxy:Boolean,tradePwd:String,phone:String,emaill:String,qq:String,wechat:String,registTime:String,currency:String,idcardName:String,state:Integer,pUserName:String,bonus:java.util.List,inviteCode:String,verifyCode:String,}
        let http_request = API.getRequest(167, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户验证码
    async verifyCode(params) {
        //{type:Integer,random:Integer,}
        let http_request = API.getRequest(178, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户信息
    async userInfo(params) {
        //{bUserId:Integer,}
        let http_request = API.getRequest(187, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //用户登出
    async loginOut(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(215, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改用户密码
    async updatePwd(params) {
        //
        let http_request = API.getRequest(249, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //修改用户支付密码
    async updateTradePwd(params) {
        //
        let http_request = API.getRequest(250, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //设置用户支付密码
    async userTradePwd(params) {
        //{pwd:String,npwd:String,tradePwd:String,ntradePwd:String,}
        let http_request = API.getRequest(251, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //系统商-用户注册
    async adminRegister(params) {
        //{id:Integer,bUserId:Integer,bUserName:String,roleId:Integer,roleName:String,password:String,phone:String,}
        let http_request = API.getRequest(257, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //会员重置密码
    async resetPwd(params) {
        //{token:String,password:String,}
        let http_request = API.getRequest(299, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //通过用户ID获取用户的归属关系
    async queryAlreadyJoinGroupByUserId(params) {
        //{bUserId:Integer,}
        let http_request = API.getRequest(325, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //刷新token
    async refreshToken(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(326, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //获取代理用户可给下级分配的奖金组
    async queryUserBonusList(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(389, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //会员重置密码-前置条件，验证用户可用的找回密码方式
    async queryUserResetType(params) {
        //{userName:String,verifyCode:String,type:String,pwdOrPhone:String,}
        let http_request = API.getRequest(406, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //代理用户注册自己的下级
    async proxyRegisterUser(params) {
        //{userName:String,password:String,isProxy:Boolean,bonus:java.util.List,}
        let http_request = API.getRequest(427, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //验证用户的手机号或密码是否正确
    async verifyUserCode(params) {
        //{userName:String,verifyCode:String,type:String,pwdOrPhone:String,}
        let http_request = API.getRequest(430, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //会员忘记自己的密码-前置验证(app)
    async validateBeforeResetPwd(params) {
        //{userName:String,verifyCode:String,type:String,pwdOrPhone:String,}
        let http_request = API.getRequest(443, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //会员修改自己的密码(app)
    async updatePwdBySelf(params) {
        //{pwd:String,npwd:String,tradePwd:String,ntradePwd:String,}
        let http_request = API.getRequest(444, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //会员修改自己的交易密码(app)
    async updateTradePwdBySelf(params) {
        //{pwd:String,npwd:String,tradePwd:String,ntradePwd:String,}
        let http_request = API.getRequest(446, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台用户修改自己的信息
    async frontUserUpdateSelfInfo(params) {
        //{id:Integer,phone:String,email:String,wechat:String,qq:String,state:Integer,nickName:String,actualName:String,remark:String,isProxy:Boolean,bonus:java.util.List,}
        let http_request = API.getRequest(450, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //重置密码  记得原本密码的情况下
    async updatePwdBySelfNeedPwd(params) {
        //{pwd:String,npwd:String,tradePwd:String,ntradePwd:String,}
        let http_request = API.getRequest(458, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //重置交易密码 记得原本密码的情况下
    async updateTradePwdBySelfNeedPwd(params) {
        //{pwd:String,npwd:String,tradePwd:String,ntradePwd:String,}
        let http_request = API.getRequest(459, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查询用户入款层级配置给前台
    async queryRechargeConfig(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(488, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //查看用户差多少打码量
    async withdrawCondition(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(503, API.version, params, false, API.cacheTime)
        //需要传入用户信息
        if (store.state.user && store.state.user.token) {
            http_request.request.headers.t = store.state.user.token
        }
        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }

    //前台登录是否需要验证码
    async loginIsNeedVerfyCode(params) {
        //{ip:String,domain:String,client:String,userId:Integer,sessionUserName:String,appId:Integer,requestCode:String,pageNum:Integer,pageSize:Integer,language:String,currency:String,}
        let http_request = API.getRequest(604, API.version, params, true, API.cacheTime)

        return post(http_request).then(http_response => {
            return Promise.resolve(http_response)
        })
    }
}

const UserAPI = new User()
export default UserAPI
