<template>
  <div class="head-container">
    <div class="header-left">
      <showAside :toggle-click="toggleClick"/>
    </div>
    <div class="header-right">
      <div class="header-user-con">

        <div class="btn-fullscreen" @click="handleFullScreen">
          <el-tooltip :content="fullscreen?`取消全屏`:`全屏`" effect="dark" placement="bottom">
            <i class="el-icon-rank"></i>
          </el-tooltip>
        </div>

        <!-- 消息中心 -->
        <div class="btn-bell">
          <el-tooltip :content="message?`有${message}条未读消息`:`消息中心`" effect="dark" placement="bottom">
            <router-link to="/tabs">
              <i class="el-icon-bell"></i>
            </router-link>
          </el-tooltip>
          <span v-if="message" class="btn-bell-badge"></span>
        </div>

        <!-- 用户名下拉菜单 -->
        <el-dropdown class="avatar-container" trigger="click">
          <div class="avatar-wrapper">
            <img class="user-avatar"
                 src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3266090804,66355162&fm=26&gp=0.jpg">
            {{ username }}
            <i class="el-icon-caret-bottom"/>
          </div>
          <el-dropdown-menu slot="dropdown" class="user-dropdown">
            <router-link class="inlineBlock" to="/">
              <el-dropdown-item>首页</el-dropdown-item>
            </router-link>
            <el-dropdown-item>个人设置</el-dropdown-item>
            <el-dropdown-item divided>
              <span style="display:block;" @click="logout">退出登陆</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>
<script>
import showAside from "./toggleleft.vue"; //引入了一个侧边栏是否折叠的组件
export default {
  // name:'header',
  components: {
    showAside
  },
  data() {
    return {
      fullscreen: false,
      name: "linxin",
      message: 2,
      username: "zyh"
    };
  },
  computed: {
    isCollapse: {
      get: function () {
        return this.$store.state.isCollapse;
      },
      set: function (newValue) {
        //this.$store.$toogleSide(newValue);//提交到vuex
        this.$store.commit('toogleSide', newValue)
      }
    }
  },
  methods: {
    toggleClick() {
      this.isCollapse = !this.isCollapse;
    },
    // 用户名下拉菜单选择事件
    logout() {
      this.$router.push("/login");
    },
    // 全屏事件
    handleFullScreen() {
      let element = document.documentElement;
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen();
        }
      }
      this.fullscreen = !this.fullscreen;
    }
  }
};
</script>

<style lang="scss" scoped>
.head-container {
  height: 20px;
  line-height: 20px;
  -webkit-box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12),
  0 0 3px 0 rgba(0, 0, 0, 0.04);
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12), 0 0 3px 0 rgba(0, 0, 0, 0.04);
  border-bottom: 1px solid #f0f0f0;
}

.header-left {
  float: left;
}

.header-right {
  float: right;
  padding-right: 20px;
}

.header-user-con {
  display: flex;
  height: 20px;
  align-items: center;
}

.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
  position: relative;
  width: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
  //margin-bottom: 10px;
}

.btn-bell {
  position: relative;
  width: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
  font-size: 24px;
  margin-right: 5px;
  //margin-bottom: 15px;

  .btn-bell-badge {
    position: absolute;
    right: 0;
    top: 4px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;

  }

  .el-icon-bell {
    color: #666;
  }
}

.user-name {
  margin-left: 10px;
}

.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}

.avatar-container {
  //height: 30px;
  display: inline-block;
  // position: absolute;
  // right: 35px;
  .avatar-wrapper {
    cursor: pointer;
    position: relative;
    line-height: initial;

    .user-avatar {
      margin-left: 20px;
      border-radius: 10px;
      height: 20px;

      img {
        display: block;
        border-radius: 50%;
      }
    }

    .el-icon-caret-bottom {
      position: absolute;
      right: -20px;
      top: 5px;
      font-size: 12px;
    }
  }
}
</style>