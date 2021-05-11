// https://segmentfault.com/a/1190000016680014
import axios from 'axios'
import Vue from 'vue'
//import qs from 'qs'

// 全局设置超时时间
axios.defaults.timeout = 10000

// 请求拦截
axios.interceptors.request.use(function (config) {
    return config
}, function (error) {
    return Promise.reject(error)
})

// 响应拦截
/*
响应结构必须是json结构
* */
axios.interceptors.response.use(function (response) {
    let data = response.data
    if (typeof (data) == 'string') data = JSON.parse(data)
    if (data['code'] === 400) {
        return Promise.reject(data)
    }
    return response
}, function (error) {
    return Promise.reject(error)
})

function getCurrentDb() {
    //打开数据库，或者直接连接数据库参数：数据库名称，版本，概述，大小
    //如果数据库不存在那么创建之
    //Demo：获取或者创建一个数据库，如果数据库不存在那么创建之
    // 1，数据库名称。
    // 2，数据库的版本号，目前来说传个1.0就可以了，当然可以不填；
    // 3，对数据库的描述。
    // 4，设置分配的数据库的大小（单位是kb）。
    // 5，回调函数(可省略)。初次调用时创建数据库，以后就是建立连接了。
    var db = openDatabase("myDb", "1.0", "it's to save demo data!", 1024 * 1024);
    return db;
}

function initDatabase() {
    var db = getCurrentDb();//初始化数据库
    if (!db) {
        alert("您的浏览器不支持HTML5本地数据库");
        return;
    }
    db.transaction(function (trans) {//启动一个事务，并设置回调函数
        //执行创建表的Sql脚本
        trans.executeSql("create table if not exists Demo(uName text null,title text null,words text null)", [],
            function (trans, result) {
            },
            function (trans, message) {
            });
    });
}

function insert() {
    //执行sql脚本，插入数据
    db.transaction(function (trans) {
        trans.executeSql("insert into Api(uName,title,words) values(?,?,?) ", [txtName, txtTitle, txtWords], function (ts, data) {
        }, function (ts, message) {
            alert(message);
        });
    });
}

function select() {
    db.transaction(function (trans) {
        trans.executeSql("select * from Api ", [], function (ts, data) {
            if (data) {
                for (var i = 0; i < data.rows.length; i++) {
                    appendDataToTable(data.rows.item(i));//获取某行数据的json对象
                }
            }
        }, function (ts, message) {
            alert(message);
            var tst = message;
        });
    });
}

//从localstorage中获取  可能为null
function storagePromise(api) {
    return new Promise((resolve) => {
        let data = localStorage.getItem(api.key)
        //

        resolve(data)
    })
}

//静态页面，静态数据 可能为null
function staticPromise(api) {
    return new Promise((resolve) => {
        if (api.localFile) {
            axios.get('/static/' + api.localFile).then(res => {
                let data = res.data
                let tmp = eval(data)
                tmp.storedTime = new Date()
                try {
                    localStorage.setItem(api.key, JSON.stringify(tmp))
                } catch (err) {
                    if (err.name == 'QuotaExceededError') {
                        // 可进行超出限定大小之后的操作，如下面可以先清除记录，再次保存
                        localStorage.clear();
                        localStorage.setItem(api.key, JSON.stringify(tmp))
                    }
                }
                resolve(res.data)
            }).catch(err => {
                console.debug(err)
                resolve(null)
            })
        } else {
            resolve(null)
        }
    })
}

//服务端请求
function serverPromise(api) {
    console.log(api.request.headers.a + '-服务端请求', api)
    return new Promise((resolve, reject) => {
        axios(api.request).then(res => {
            let data = res.data
            let tmp = eval(data)
            tmp.storedTime = new Date()
            try {
                localStorage.setItem(api.key, JSON.stringify(tmp))
            } catch (err) {
                if (err.name == 'QuotaExceededError') {
                    // 可进行超出限定大小之后的操作，如下面可以先清除记录，再次保存
                    localStorage.clear();
                    localStorage.setItem(api.key, JSON.stringify(tmp))
                }
            }
            data = tmp
            resolve(res.data)
        }).catch(err => {
            reject(err)
        })
    })
}

//post请求 处理方式  最后返回的是一个json对象
export async function post(api) {
    if (api.cacheable && api.cacheable === true) {
        return storagePromise(api)
            //从storage获取，没有则从静态数据获取
            .then(response => {
                if (response) {
                    let localData = JSON.parse(response)
                    let createTime = localData['storedTime']
                    if (createTime && api.cacheTime * 60 * 1000 < (new Date().getTime() - new Date(createTime).getTime())) {
                        //异步更新，但是还是使用storage数据
                        serverPromise(api)
                        return Promise.resolve(response)
                    } else if (createTime) {
                        //使用storage
                        return Promise.resolve(response)
                    } else {
                        //storage没有数据 使用静态数据
                        return staticPromise(api)
                    }
                } else {
                    return staticPromise(api)
                }
            })
            //对结果进行处理，如果结果为空，只能去请求服务器了
            .then(response => {
                if (response) {
                    if (typeof (response) == 'string') response = JSON.parse(response)
                    return Promise.resolve(response)
                } else {
                    return serverPromise(api)
                }
            }).catch(err => {
                return Promise.reject(err)
            })
    } else {
        return serverPromise(api)
    }
}

//将方法挂载到Vue原型上
Vue.prototype.post = post