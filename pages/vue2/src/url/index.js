//  "URL": { "版型标识": "添加到default后的后缀(可以是版本号)", "default": "实际页面文件地址" },
const url = {
  "/": { "default": "home/home.vue" },
  "/found": { "default": "home/home.vue" },
  "/welfare": { "default": "home/home.vue" },
  "/user": { "default": "user/user.vue" }
}
function getRoute(path, frame) {
  if (url[path]) {
    if (url[path][frame]) {
      return url[path]["default"] + url[path][frame];
    } else {
      return url[path]["default"]
    }
  }
}
export { url, getRoute }