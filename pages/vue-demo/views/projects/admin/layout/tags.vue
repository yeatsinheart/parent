<template>
  <!-- 打开标签的容器 -->
  <div class="tags">
    <ul>
      <li v-for="(item,index) in tagsList" :key="index" :class="{'active': isActive(item.title)}" class="tags-li">
        <span :frameid="item.url" class="tags-li-title" @click="active(item)">{{ item.title }}</span>
        <span class="tags-li-icon" @click="closeTags(index)"><i class="el-icon-close"></i></span>
      </li>
    </ul>
    <div class="tags-close-box">
      <el-dropdown @command="handleCommand">
        <el-button size="mini" type="primary">
          标签操作
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown" size="small">
          <el-dropdown-item command="closeOther">关闭其他</el-dropdown-item>
          <el-dropdown-item command="all">关闭所有</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
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
      },
      set: function (newValue) {
        this.$store.commit("TAGES_LIST", newValue);
        // this.$store.state.tagsList = newValue;
      }
    }
  },
  watch: {
    //监听路由变化
    $route(newValue, oldValue) {
      console.log(oldValue)
      this.setTags(newValue);
    }
  },
  methods: {
    //选中的高亮
    isActive(title) {
      if (this.$store.state.tagActive) {
        return title === this.$store.state.tagActive.title;
      }
      return false;
    },
    active(item) {
      this.$store.commit('activeTag', item)
    },
    handleCommand(command) {
      if (command == "closeOther") {
        // 关闭其他标签
        const curItem = this.tagsList.filter(item => {
          return item.path === this.$route.fullPath;
        });
        this.tagsList = curItem;
      }
    },
    //添加标签
    setTags(route) {
      let isIn = this.tagsList.some(item => {
        //判断标签是否存在
        return item.url === route.url;
      });
      //不存在
      if (!isIn) {
        // 判断当前的标签个数
        if (this.tagsList.length >= 10) {
          // messages("warning", "当标签大于10个，请关闭后再打开");
        } else {
          this.tagsList.push({
            title: route.title,
            url: route.url
          });
          //存到vuex
          //this.$store.commit("TAGES_LIST", this.tagsList);
        }
      }
    },
    closeTags(index) {
      // 操作后会导致所有的tags重新刷一遍
      let tags = this.tagsList.splice(index, 1);
      console.log("当前激活", this.$store.state.tagActive.title, tags[0], this.isActive(tags[0].title))
      if (!this.isActive(tags[0].title)) {
        return;
      }
      let total = this.tagsList.length;
      if (total === 0) {
        return;
      }
      console.log("剩下", total, index)
      if (index < total) {
        console.log("激活这个", this.tagsList[index])
        this.active(this.tagsList[index])
      } else {
        console.log("激活这个", this.tagsList[total - 1])
        this.active(this.tagsList[total - 1])
      }
      console.log(tags)
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
</style>
<style>
.tags .el-scrollbar .scrollbar-wrapper .el-scrollbar__view {
  overflow: initial !important;
}
</style>