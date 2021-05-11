export default class API {
    static version = process.env.VUE_APP_DEFAULT_API_VERSION || '1.0.0'
    static cacheTime = process.env.VUE_APP_DEFAULT_API_CACHE_TIME || 0
    language
    currency
    method
    api
    params
    cacheable
    key
    localFile
    request
    cacheTime

    constructor() {
    }

    static getRequest(apiId, version, param, cacheable, cacheTime) {
        let api = {};
        let language = localStorage.getItem('choosed-language') || localStorage.getItem('application-default-language') || process.env.VUE_APP_LANGUAGE
        let currency = localStorage.getItem('choosed-currency') || localStorage.getItem('application-default-currency') || process.env.VUE_APP_CURRENCY
        let host = process.env.VUE_APP_DEV_HOST || window.location.hostname
        // localStorage 的 key值 语言-币种-接口
        // 伪静态接口 存储到本地json文件名 语言-币种-接口
        //api.localFile = host + '-' + language + '-'+apiId+'.json'
        // 异步刷新时间（分钟）

        // 是否需要反向代理
        let source = host.slice(0, 2)
        let browserClient = 'H5'
        if (source != 'm.') browserClient = 'PC'
        let paramValues = param ? [param] : []
        api.cacheable = cacheable
        if (paramValues.length === 0) {
            api.cacheable = true
        }
        api.cacheTime = cacheTime
        api.key = language + '-' + currency + '-' + browserClient + '-' + apiId
        api.request = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
                'r': Math.random().toString(36).slice(-8),
                'client': browserClient,
                'h': host,
                'c': currency,
                'l': language,
                'a': apiId + '-' + version
            },
            method: 'post',
            url: '/gate',
            data: {paramValues}
        }
        return api
    }


}