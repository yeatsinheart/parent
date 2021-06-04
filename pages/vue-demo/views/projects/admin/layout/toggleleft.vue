<template>
  <div>
    <!--    @click="toggleClick"-->
    <!--    <div class="showAside pull-left">
          <span @click="toogle">
            <i :class="menuShow===true?'el-icon-s-unfold':'el-icon-s-fold'"></i>
            <span v-if="menuShow">隐藏导航栏</span>
            <span v-if="!menuShow">显示导航栏</span>
          </span>
          &lt;!&ndash;      <template v-for="(item,index) in items">
                  <span :key="index"><i :class="item.icon"></i><span slot="title">{{ item.name }}</span>
                    </span>
                </template>&ndash;&gt;
        </div>-->
    <div class="handle-button" style="top: 5px; background-color: rgb(24, 144, 255);" @click="toogle">
      <span>
        <i :class="menuShow===true?'el-icon-s-fold':'el-icon-s-unfold'"></i>
        </span>
    </div>

<!--    <el-drawer
        :before-close="toogle"
        :visible.sync="menuShow"
        direction="ltr"
        size=180
        title="导航栏"
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
    </el-drawer>-->


    <div class="menu" :visible.sync="menuShow">
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
.container_collapse .handle-button {
  left: 0px;
}
.menu{
  width: 180px;
  left: -180px;
  position: absolute;
  height: 100%;
  border-right: 1px dashed #b0b0b0;
}

.handle-button {
  z-index: 20003;
  width: 30px;
  height: 30px;
  position: absolute;
  left: 0px;
  text-align: center;
  font-size: 24px;
  border-radius: 0px 6px 6px 0px !important;
  pointer-events: auto;
  cursor: pointer;
  color: #fff;
  line-height: 30px;
}

.handle-button i {
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

.clearfix {
  float: left;
}

.showAside {
  text-align: left;
  margin-left: 10px;
  cursor: pointer;
  line-height: 20px;

  i {
    font-size: 20px;
  }

  span {
    display: inline-block;
    height: 20px;

  }
}
</style>