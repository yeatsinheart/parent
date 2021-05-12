<template>
  <div class="wrapper">
    <Aside class="aside-container"/>
    <div :class="isCollapse==true?'container_collapse':''" class="main-container">
      <Header/>
      <div class="container">
        <tags/>
        <div class="contents">
          <transition mode="out-in" name="fade-transform">
            <router-view></router-view>
          </transition>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Aside from "../../layout/leftside.vue";
import Header from "../../layout/topside.vue";
import Tags from '../../layout/tags.vue';

import {mapState} from "vuex";

export default {
  name: "Layout",
  components: {
    Aside,
    Header,
    Tags,
  },
  computed: {...mapState(["isCollapse"])}
};
</script>
<style lang="scss" scoped>
.wrapper {
  position: relative;
  height: 100%;
  width: 100%;

  .main-container {
    min-height: 100%;
    -webkit-transition: margin-left 0.28s;
    transition: margin-left 0.28s;
    margin-left: 180px;
    position: relative;
  }

  .container_collapse {
    margin-left: 64px;
  }
}
</style>