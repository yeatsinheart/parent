<template>
  <!-- 打开标签的容器 -->
  <div>
    <div class="tags">

      <ul>
        <li v-for="(item,index) in tagsList" :key="index" :class="{'active': isActive(item.id)}" class="tags-li">
          <el-dropdown>
            <span :frameid="item.url" class="tags-li-title" @click="active(item)">{{ item.name }}</span>
            <span class="tags-li-icon" @click="closeTags(index)"><i class="el-icon-close"></i></span>
            <el-dropdown-menu slot="dropdown" size="small">
              <el-dropdown-item @click.native="save(index)">关闭其他</el-dropdown-item>
              <el-dropdown-item @click.native="closeTags()">关闭所有</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </li>
      </ul>
      <!--    <div class="tags-close-box">
            <el-dropdown @command="handleCommand">
              <el-button size="mini" type="primary">
                标签操作
                <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown" size="small">
                <el-dropdown-item command="closeOther">关闭其他</el-dropdown-item>
                <el-dropdown-item command="all">关闭所有</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>-->
    </div>
    <div class="frame">
      <template v-for="(item,index) in tagsList">
        <div :key="index" :class="isActive(item.id)?'active':'hidden'" class="page">
          <iframe :src="item.url" border="0" height="100%" scrolling="no" width="100%"/>
        </div>
      </template>
    </div>
  </div>
</template>
<script>

import adminTabService from "@/core/service/AdminTabService";

export default {
  created() {
    //判断标签里面是否有值 有的话直接加载
    if (this.tagsList.length == 0) {
      //this.setTags(this.$route);
      //this.setTags(this.$route);
    }
  },
  computed: {
    //computed 方法里面没有set方法因此不能使用mapState,需要重新定义set方法
    tagsList: {
      get: function () {
        return this.$store.state.tagsList;
      }
    }
  },
  watch: {
  },
  methods: {
    //选中的高亮
    isActive(id) {
      return adminTabService.isActive(id);
    },
    active(tab) {
      adminTabService.activeTab(tab)
    },
    save(index) {
      adminTabService.onlyKeepThis(index)
    },
    closeTags(index) {
      adminTabService.closeTabs(index)
    }
  }
};
</script>
<style lang="scss" scoped>
.tags {
  position: relative;
  width: 100%;
  height: 30px;
  overflow: hidden;
  background: #ffffff;
  padding-right: 100px;
  box-shadow: 0 5px 10px #ddd;
  z-index: 10;
}

.tags ul {
  box-sizing: border-box;
  width: 1000%;
  height: 100%;
}

.tags-li {
  float: left;
  margin: 3px 5px 2px 3px;
  border-radius: 3px;
  font-size: 12px;
  overflow: hidden;
  cursor: pointer;
  height: 23px;
  line-height: 23px;
  border: 1px solid #e9eaec;
  background: #fff;
  padding: 0 5px 0 12px;
  vertical-align: middle;
  -webkit-transition: all 0.3s ease-in;
  -moz-transition: all 0.3s ease-in;
  transition: all 0.3s ease-in;
}

.tags-li:not(.active):hover {
  background: #fff;
}

.tags-li.active {
  background-color: rgb(24, 144, 255);
  border-color: rgb(24, 144, 255);
}

.tags-li-title {
  float: left;
  max-width: 80px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-right: 5px;
}

.tags-li.active .tags-li-title {
}

.tags-close-box {
  position: absolute;
  right: 0;
  top: 0;
  box-sizing: border-box;
  padding-top: 1px;
  text-align: center;
  // width: 110px;
  height: 30px;
  box-shadow: -3px 0 15px 3px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.frame {
  position: absolute;
  width: 100%;
  height: calc(100% - 30px);

  .page {
    position: absolute;
    width: 100%;
    height: 100%;
  }

  .active {
    display: block;
  }

  .hidden {
    display: none;
  }
}
</style>
<style>
.tags .el-scrollbar .scrollbar-wrapper .el-scrollbar__view {
  overflow: initial !important;
}
</style>