<template>
  <div>
    <!--    @click="toggleClick"-->
    <div class="showAside pull-left">
      <span @click="toogle">
        <i :class="menuShow===true?'el-icon-s-unfold':'el-icon-s-fold'"></i>
        <span v-if="menuShow">隐藏导航栏</span>
        <span v-if="!menuShow">显示导航栏</span>
      </span>
      <!--      <template v-for="(item,index) in items">
              <span :key="index"><i :class="item.icon"></i><span slot="title">{{ item.name }}</span>
                </span>
            </template>-->
    </div>

    <el-drawer
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
    </el-drawer>
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