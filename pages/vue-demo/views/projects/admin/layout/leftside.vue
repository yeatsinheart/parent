<template>
  <div class="aside">
    <el-drawer
        direction="ltr"
        title="我是标题"
        visible.sync="false"
    >
      <el-input
          v-model="filterText"
          placeholder="输入关键字进行过滤">
      </el-input>

      <el-tree
          ref="tree"
          :data="items"
          :filter-node-method="filterNode"
          :props="defaultProps"
          class="filter-tree"
          default-expand-all
          @node-click="clickNode">
      </el-tree>
    </el-drawer>
    <!--

        <el-menu :collapse="isCollapse" :default-active="onRoutes"
                 active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
                 text-color="#fff"
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
        </el-menu>-->

  </div>
</template>

<style lang="scss" scoped>
.aside {
  -webkit-transition: width 0.28s;
  transition: width 0.28s;
  height: 100%;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1001;
  overflow: hidden;
  /*
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
    }*/
}
</style>
<script>

import adminMenuService from "@/core/service/AdminMenuService";
import adminTabService from "@/core/service/AdminTabService";

export default {
  data() {
    return {
      filterText: '',
      items: [],
      defaultProps: {
        children: 'subs',
        label: 'name'
      }
    };
  },
  computed: {},
  mounted() {
    adminMenuService.getMenu().then(response => {
      this.items = response
    })
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },

    clickNode(clickNode) {
      adminTabService.open(clickNode.name, clickNode.url)
    }
  }, watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
};
</script>