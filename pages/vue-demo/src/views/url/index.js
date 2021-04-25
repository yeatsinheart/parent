// URL: {
//     版型标识: '添加到default后的后缀(可以是版本号)',
//     default: '实际页面文件地址'
//   },
const url = {
  '/': { default: '@/views/Home.vue' },
  '/about': { default: '@/views/About.vue' }
}

function getRoute (path, frame) {
  if (url[path]) {
    if (url[path][frame]) {
      return url[path][frame]
    } else {
      return url[path].default
    }
  }
}

// 支持子组件
const hasChild = (path) => {
  return url[path].children
}
export { url, getRoute, hasChild }
