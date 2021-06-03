<template>
  <div class="wrapper">
    <!--    <Aside class="aside-container"/>-->
    <div :class="menuShow===false?'container_collapse':''" class="maincontainer">
      <showAside/>
      <Header/>
      <div class="container">
        <tags/>
        <!--        <template class="contents">
                  <transition mode="out-in" name="fade-transform">
                  </transition>
                </template>-->
      </div>
    </div>
  </div>
</template>
<script>
import showAside from "../../layout/toggleleft.vue"; //引入了一个侧边栏是否折叠的组件
import Header from "../../layout/topside.vue";
import Tags from '../../layout/tags.vue';
import {mapState} from "vuex";

export default {
  name: "Layout",
  components: {
    Header,
    Tags,
    showAside
  },
  computed: {
    ...mapState(["menuShow"]),
  }, methods: {}
};
</script>
<style lang="scss" scoped>
.wrapper {
  position: relative;
  height: 100%;
  width: 100%;

  .maincontainer {
    height: 100%;
    -webkit-transition: margin-left 0.28s;
    transition: margin-left 0.28s;
    margin-left: 180px;
    position: relative;

    .container {
      position: absolute;
      width: 100%;
      height: calc(100% - 20px);
    }
  }

  .container_collapse {
    margin-left: 0;
  }
}
</style>