import Vue from 'vue'
// import {createVueObj, deleteVueObj} from './dynamicVueObject';
// import alertPane from "../views/common/popup/alertPane";
const event = new Vue()
export default event

/* 全局异步事件  跨页面事件发生时的处理 */
const prompt = () => import('./prompt')
event.$on('test', msg => {
    prompt().then(o => {
        o.default(msg)
    })
})

