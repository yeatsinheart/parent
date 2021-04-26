
# 用户端项目
每个版型需要添加一个.env.自定义名字，
VUE_APP_PROJECT_NAME=窗口标题名字
VUE_APP_DEFAULT_CLIENT=h5或者pc
VUE_APP_DEFAULT_STYLE=自定义名字

且package.json文件中的scripts添加自定义名字的脚本名，npm run 自定义名字

宽度，高度，提前限定，一定比例。
pc 宽度最大到1080px 10rem 如果设计图不是1080，则设计图等比例缩小到1080px，计算rem值
H5 宽度最大到540px 10rem 如果设计图不是540，则设计图等比例缩小到1080px，计算rem值

（设计图元素宽或高度/设计图总宽度）*10 =该元素的宽或高rem值 精确到每个元素的rem值。实现等比例缩放。

页面元素布局时，
考虑到padding等因素，不要使用100%。而是自己根据最大的rem值。直接写rem值。

项目结构
public
  -- 版型-客户端.html    
  
--src
  --router       需要特别定义的路由
  --core          业务功能定义（复用）
    --dto         数据对象：应用，用户，游戏，活动，公告，充值，提现
    --service     负责所有数据逻辑，依赖自己的mapper
    --mapper      负责需要网络请求的方法，依赖自己的api
    --api         负责接口信息定义（是否可缓存，缓存key值，可缓存时间，本地静态json文件名，后端接口信息），调用axios
    
  --comments      存放工具：事件，动态插入页面组件
  --language      存放独立语言文件（会自动加载，参考已有的模式）
  原则：没有不变的页面，页面组件只能满足个人意淫
  --views         存放页面，只与页面有关，只能跟core中的service有调用关系。不能自己实现网络请求
    --common      可以复用的页面元素放到common：弹出框等
    -- ……         可以参考用的的业务页面：注册，登陆，查看详情  
    --版型         版型（独立）        process.env.VUE_APP_DEFAULT_STYLE 
      --版型.js    vue入口js
      --版型.vue   vue入口vue
      --客户端     h5 或者 pc  process.env.VUE_APP_DEFAULT_CLIENT
        --        【定制/区别于通用的】页面或者页面元素
        --page      该版型的页面。先以行为单位再以列，拆分视图组件，如果一个页面有多个组件组成，那么需要给这个页面创建一个文件夹，这个页面放到文件夹的index中

# 多语言模块格式：
当前页面组件中  页面全路径以-号分割
export const langMsg ={
    'zh-CN':{
      'layout-header-brand':{
        'home':'欢迎光临',
        'found':'中奖信息',
        'welfare':'福利中心',
        'myself':'个人中心',
        'language':'语言设置',
        'theme':'主题设置',
        'router':'页面路由'
      }
    }
  }
切换语言时调用
this.$root.$languageChange(language)
this.$store.commit('changeLanguage',language)
# 多主题 需要做的 不同语言可能样式也需要调整 <style lang="scss" scoped>
   版型->客户端->style->_variables.scss  主题名字不要添加 "theme-" 
   版型应该对 背景 字体 边框设置主次颜色，其余自定义的则在页面中填写
    所有颜色值都需要放到主题中，如果不知道的。就放到default中
     使用
     $tmp: (
       主题/语言:('键key': 值value)
      );
     @include getSelector($tmp, 'theme-') {
           background: getContent("k");
      }
     
     组件添加
     template层最外层套上<div :class="theme">
     添加theme计算属性：computed: {
           theme: function () {
             return this.$store.state.choosedTheme
           },
           language: function () {
             return 'language-'+this.$store.state.choosedLanguage
           }
         }
     需要多主题的style中，颜色属性的地方使用这个
     
          $tmp: (
                 default:('k': black)
                 red:('k': red)
                 black:('k': black)
                );
                 @include getSelector($tmp, 'theme-') {
                     background: getContent("k");
                }
