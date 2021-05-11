// 用于动态向全局(main-content.vue)添加vue对象，常用于类似弹窗的功能等临时对象的添加
import Vue from 'vue'
import EVENT from './event/event'

let dynamicContainer = Vue.extend({
    created() {
        // 支持全局调用临时添加，用完即删型vue组件
        EVENT.$on('addVueObj', (view, attr, event) => {
            // 只允许新增一个
            this.allGlobleVue = []
            this.allGlobleVue.push({view, attr, event})
        })
        EVENT.$on('deleteVueObj', (vueObj) => {
            this.allGlobleVue.splice(this.allGlobleVue.findIndex(item => {
                return item.view === vueObj
            }), 1)
        })
    }, data() {
        return {
            allGlobleVue: [] // 临时动态添加的窗体，例如弹窗等
        }
    }, render(createElement) {
        return createElement('div', {}, this.allGlobleVue.map(item => {
                console.log(item)
                return createElement(item.view, {name: 'dynamic', props: item.attr, on: item.event})
            })
        )
    }
})
let dynamicContainerInstance = new dynamicContainer({
    el: document.createElement('div')
})
// 视图body中添加
document.body.appendChild(dynamicContainerInstance.$el)

/*
放到new promise中获取视图处理结果。(懒加载方式)
创建这个视图组件，并返回这个组件的对象，需要对这个对象进行destroyViewObj操作才行
let dyVueObj = createViewObj(引用的视图,参数，事件对象)
能被动态创建的视图组件，需要指定方法提供与传入事件中同名的的方法名
this.$emit('动态事件名【success/fail】', msg)
传入事件对象格式
{
      success(res) {
        destroyViewObj(dyVueObj)// 点完ok关闭弹窗
        resolve(res)
      },
      fail() {
        destroyViewObj(dyVueObj)// 点完cancel关闭弹窗
        reject()
      }
}
*/
export function createViewObj(view, attr, event) {
    view().then(obj => {
        let vueObj = Vue.extend(obj.default)
        EVENT.$emit('addVueObj', vueObj, attr, event)
        return vueObj
    })

}

export function destroyViewObj(obj) {
    EVENT.$emit('deleteVueObj', obj)
}