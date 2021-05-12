<template>
  <div class="aside">
    <el-menu :collapse="isCollapse" :default-active="onRoutes"
             active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
             text-color="#fff"
             unique-opened="true"
             @close="handleClose"
             @open="handleOpen"
             @select="select"
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :key="item.index" :index="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu v-if="subItem.subs" :key="subItem.index" :index="subItem.index">
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.index">
                  {{ threeItem.title }}
                </el-menu-item>
              </el-submenu>
              <el-menu-item v-else :key="subItem.index" :index="subItem.index">
                {{ subItem.title }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :key="item.index" :index="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<style lang="scss" scoped>
.aside {
  -webkit-transition: width 0.28s;
  transition: width 0.28s;
  height: 100%;
  position: fixed;
  font-size: 0px;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1001;
  overflow: hidden;
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 180px;
    height: 100%;
    text-align: left;
  }
  .el-menu--collapse {
    height: 100%;
  }
  ::v-deep .el-menu-item, ::v-deep .el-submenu__title {
    height: 20px;
    line-height: 20px;
  }
}
</style>
<script>

import {mapState} from "vuex";

export default {
  data() {
    return {
      //配置目录
      items: [
        {
          icon: "el-icon-edit-outline",
          index: "home",
          title: "系统首页"
        },
        {
          icon: "el-icon-edit-outline",
          index: "icon",
          title: "自定义图标"
        },
        {
          icon: "el-icon-edit-outline",
          index: "component",
          title: "组件",
          subs: [
            {
              index: "editor",
              title: "富文本编译器"
            },
            {
              index: "countTo",
              title: "数字滚动"
            },
            {
              index: "trees",
              title: "树形控件",
              subs: [
                {
                  index: "tree",
                  title: "自定义树"
                },
                {
                  index: "treeSelect",
                  title: "下拉树"
                }
                // ,{
                //   index:'treeTable',
                //   title:'表格树',
                // }
              ]
            },
          ]
        },
        {
          icon: "el-icon-edit-outline",
          index: "draggable",
          title: "拖拽",
          subs: [
            {
              index: "draglist",
              title: "拖拽列表"
            },
            {
              index: "dragtable",
              title: "拖拽表格"
            }
          ]
        },
        {
          icon: "el-icon-edit-outline",
          index: "charts",
          title: "图表",
          subs: [
            {
              index: "cricle",
              title: "饼图"
            },
          ]
        },
        {
          icon: "el-icon-edit-outline",
          index: "7",
          title: "错误处理",
          subs: [
            {
              index: "permission",
              title: "权限测试"
            },
            {
              index: "404",
              title: "404页面"
            }
          ]
        },
      ],
    };
  },
  computed: {
    ...mapState(["isCollapse"]),
    onRoutes() {
      return this.$route.path.replace("/", "");
    },
  },
  methods: {
    //sub-menu 展开的回调	index: 打开的 sub-menu 的 index， indexPath: 打开的 sub-menu 的 index path
    handleOpen(key, keyPath) {
      console.log("展开", key, keyPath);
    },
    //sub-menu 收起的回调	index: 收起的 sub-menu 的 index， indexPath: 收起的 sub-menu 的 index path
    handleClose(key, keyPath) {
      console.log("折叠", key, keyPath);
    },
    select(index, indexPath) {
      // 选中
      //菜单激活回调	index: 选中菜单项的 index, indexPath: 选中菜单项的 index path
      console.log("跳转页面", index, indexPath);
      this.$store.commit('taglist', {'id': index, 'title': index, "url": "http://www.baidu.com"})

    }
  }
};
</script>