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
              <el-dropdown-item @click.native="refresh(index,item.url)">刷新</el-dropdown-item>
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
          <iframe :src="item.url" :id="'ifrmname'+index" height="100%"  width="100%" style="border:0;"/>
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
    },
    refresh(index,url) {
      document.getElementById('ifrmname'+index).src=url;
      document.getElementById('ifrmname'+index).contentWindow.location.reload(true);
    }
  }
};
</script>
<style lang="scss" scoped>
.tags {
  position: relative;
  width: 100%;
  overflow: hidden;
  background: #ffffff;
  padding-left: 30px;
  margin-bottom: 2px;
  z-index: 10;
  -webkit-box-shadow: 0 1px 3px 0 rgb(0 0 0 / 12%), 0 0 3px 0 rgb(0 0 0 / 4%);
  box-shadow: 0 1px 3px 0 rgb(0 0 0 / 12%), 0 0 3px 0 rgb(0 0 0 / 4%);
  border-bottom: 1px solid #f0f0f0;
}

.tags ul {
  box-sizing: border-box;
  width: 1000%;
  height: 100%;
}

.tags-li {
  float: left;
  border-radius: 3px;
  font-size: 12px;
  overflow: hidden;
  cursor: pointer;
  border: 1px solid #e9eaec;
  background: #fff;
  vertical-align: middle;
  -webkit-transition: all 0.3s ease-in;
  -moz-transition: all 0.3s ease-in;
  transition: all 0.3s ease-in;
  margin-left: 5px;
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
  color:black;
  padding-left: 5px;
  max-width: 80px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-right: 5px;
}
.tags-li-icon{
  line-height: 20px;
}
.tags-li.active .tags-li-title {
  color:white;
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
  height: calc(100% - 23px);

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
<style lang="scss" scoped>
.tags .el-scrollbar .scrollbar-wrapper .el-scrollbar__view {
  overflow: initial !important;
}
</style>