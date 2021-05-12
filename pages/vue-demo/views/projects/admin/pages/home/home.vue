<template>
  <div class="wrapper">
    <Aside class="aside-container"/>
    <div :class="isCollapse==true?'container_collapse':''" class="maincontainer">
      <Header/>
      <div class="container">
        <tags/>
        <div class="frame">
          <template v-for="(item,index) in tagsList">
            <div :key="index" :class="isActive(item.id)?'active':'hidden'" class="page">
              <iframe :src="item.url" border="0" height="100%" scrolling="no" width="100%"/>
            </div>
          </template>
        </div>
        <!--        <template class="contents">
                  <transition mode="out-in" name="fade-transform">
                  </transition>
                </template>-->
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
  computed: {
    ...mapState(["isCollapse"]),
    ...mapState(["tagsList"]),
  }, methods: {
    //选中的高亮
    isActive(id) {
      if (this.$store.state.tagActive) {
        return id === this.$store.state.tagActive.id;
      }
      return false;
    }
  }
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
    }
  }

  .container_collapse {
    margin-left: 64px;
  }
}
</style>