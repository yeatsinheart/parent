import AppAPI from '@/core/api/application/App'

/*
服务类，某一个模块对象的所有相关方法。
返回值都为promise。所以需要前端自己then解决问题
正常且成功情况下，需要调用.then 自己在方法体内赋值
*/
class ApplicationService {

    async getApplication() {
        return AppAPI.getAppInfo()
    }

    //获取站点Logo
    async getApplicationLogo() {
        return this.getApplication().then(response => {
            // console.log('请求结果',typeof (response),response)
            this.name = response['data']['appName']
            return Promise.resolve(response['data']['logoUrl'])
        })
    }

    //获取站点名字
    async getApplicationName() {
        return this.getApplication().then(response => {
            return Promise.resolve(response['data']['appName'])
        })
    }

    // 获取默认语言
    async getApplicationDefaultLanguage() {
        return this.getApplication().then(response => {
            return Promise.resolve(response['data']['defaultlanguage'])
        })
    }

    //获取默认币种
    async getApplicationDefaultCurrency() {
        return this.getApplication().then(response => {
            return Promise.resolve(response['data']['defaultcurrency'])
        })
    }

    //获取软件的ICON
    async getAppIcon(client) {
        return this.mapper.getDownLoadVersionInfo(client)
    }

}

const applicationService = new ApplicationService()

export default applicationService
