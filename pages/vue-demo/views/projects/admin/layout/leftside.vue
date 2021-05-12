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
          <el-submenu :key="item.id" :index="item.id">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.name }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu v-if="subItem.subs" :key="subItem.id" :index="subItem.id">
                <template slot="title">{{ subItem.name }}</template>
                <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i" :index="threeItem.id">
                  {{ threeItem.name }}
                </el-menu-item>
              </el-submenu>
              <el-menu-item v-else :key="subItem.id" :index="subItem.id">
                {{ subItem.name }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :key="item.id" :index="item.id">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
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
import adminMenuService from "@/core/service/AdminMenuService";

export default {
  data() {
    return {

      //配置目录
      items: [],
    };
  },
  computed: {
    ...mapState(["isCollapse"]),
    onRoutes() {
      return this.$route.path.replace("/", "");
    },
  },
  mounted() {
    adminMenuService.getMenu().then(response => {
      console.log(response)
      this.items = response
    })
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
      let clickNode = adminMenuService.findNode(this.items, indexPath);
      console.log("跳转页面", clickNode);
      this.$store.commit('taglist', {'id': clickNode.id, 'name': clickNode.name, "url": clickNode.url})

    }
  }
};
</script>