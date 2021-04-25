import { createRouter, createWebHistory } from 'vue-router'
import { url, getRoute, hasChild } from '@/views/url/index'

const routes = []
for (const key in url) {
// frame 切换
  const frame = localStorage.getItem('choosed-frame') || localStorage.getItem('application-default-frame') || process.env.ACTIVE_VIEW_PACKAGE
  const filePath = getRoute(key, frame)
  const metaInfo = {}
  const child = hasChild(key)
  if (filePath) {
    const obj = {
      path: key,
       component: () => import(/* webpackChunkName: "chunk-vendors" */`${filePath}`),
      // component: resolve => require([`${filePath}`], resolve),
      meta: metaInfo
    }
    typeof child === 'object' && (obj.children = child)
    routes.push(obj)
  }
}
console.log(routes)

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
