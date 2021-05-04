import {createViewObj, destroyViewObj} from '../dynamicVueObject'
// createVueObj  全局生成组件函数
// deleteVueObj  全局删除由createVueObj生成的组件
//import promptVue from '../../views/common/popup/prompt.vue'
// 异步视图 如果有自定义存在。自定义还是通用界面？
const promptVue = () => import('commonpage/page/popup/prompt.vue')

export function test(title, initValue) {
    new Promise((resolve, reject) => {
        let dyVueObj = createViewObj(promptVue, {title, initValue}, {
            ok(res) {
                destroyViewObj(dyVueObj)// 点完ok关闭弹窗
                resolve(res)
            }, cancel() {
                destroyViewObj(dyVueObj)// 点完cancel关闭弹窗
                reject()
            }
        })
    })
}