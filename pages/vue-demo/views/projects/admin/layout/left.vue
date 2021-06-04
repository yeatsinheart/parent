<template>
  <div :class="menuShow===false?'menu-hidden':'menu-show'" class="left">
    <div class="menu-tool" @click="toogle">
      <span>
        <i :class="menuShow===true?'el-icon-s-fold':'el-icon-s-unfold'"></i>
        </span>
    </div>
    <div :class="menuShow===false?'hiden':''" class="menu">
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
    </div>
  </div>
</template>
<script>

import {mapState} from "vuex";
import adminMenuService from "@/core/service/AdminMenuService";
import adminTabService from "@/core/service/AdminTabService";

export default {
  name: "showAside",
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
  props: {}, computed: {
    ...mapState(["menuShow"]),
  },
  mounted() {
    adminMenuService.getMenu().then(response => {
      this.items = response
    })
  },
  methods: {
    toogle() {
      this.$store.commit("menuShowToggle", this.isCollapse)
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },

    clickNode(clickNode) {
      // 有子菜单的不打开tab页
      if(clickNode['subs'] && clickNode['subs'].length>0){
        return;
      }
      adminTabService.open(clickNode.name, clickNode.url)
    }
  }, watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },
};
</script>
<style lang="scss" scoped>
.left {
  z-index: 20003;
  width: 180px;
  height: 100%;
  background: #fff;
  position: absolute;
}

.menu-hidden.left {
  width: 0;
}

.menu {
  width: 180px;
  height: 100%;
  position: absolute;
  border-right: 1px dashed #b0b0b0;
}

.menu-hidden .menu {
  display: none;
}

.menu-tool {
  top: 5px;
  background-color: rgb(24, 144, 255);
  left: 180px;
  z-index: 20003;
  width: 30px;
  height: 30px;
  position: absolute;
  text-align: center;
  border-radius: 0 6px 6px 0 !important;
  pointer-events: auto;
  cursor: pointer;
  color: #fff;
}

.menu-hidden .menu-tool {
  left: 0;
}

.menu-tool i {
  font-size: 24px;
  line-height: 30px;
}

[class*=" el-icon-"], [class^=el-icon-] {
  font-family: element-icons !important;
  speak: none;
  font-style: normal;
  font-weight: 400;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  vertical-align: baseline;
  display: inline-block;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

</style>