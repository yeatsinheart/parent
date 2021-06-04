<template>
  <div class="wrapper">
    <!--    <Aside class="aside-container"/>-->
    <Menu/>
    <div :class="menuShow===false?'menu_collapse':''" class="maincontainer">
      <Header/>
      <div class="container">
        <tags/>
      </div>
    </div>
  </div>
</template>
<script>
import Menu from "../../layout/left.vue"; //引入了一个侧边栏是否折叠的组件
import Header from "../../layout/topside.vue";
import Tags from '../../layout/tags.vue';
import {mapState} from "vuex";

export default {
  name: "Layout",
  components: {
    Header,
    Tags,
    Menu
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
    margin-left: 180px;
    height: 100%;
    -webkit-transition: margin-left 0.28s;
    transition: margin-left 0.28s;
    position: relative;

    .container {
      position: absolute;
      width: 100%;
      height: calc(100% - 20px);
    }
  }

  .menu_collapse {
    margin-left: 0;
  }
}

@media only screen and (max-width: 470px) {
  .maincontainer {
    margin-left: 0 !important;
  }
}
</style>