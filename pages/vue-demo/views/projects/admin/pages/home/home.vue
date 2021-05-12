<template>
  <div class="wrapper">
    <Aside class="aside-container"/>
    <div :class="isCollapse==true?'container_collapse':''" class="main-container">
      <Header/>
      <div class="container">
        <tags/>
        <template class="contents">
          <transition mode="out-in" name="fade-transform">
            <template v-for="(item,index) in tagsList">
              <div :key="index" :class="isActive(item.title)?'active':'hidden'" class="page">
                <iframe :src="item.url" border="0" height="100%" width="100%"/>
              </div>
            </template>
          </transition>
        </template>
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
    tagsList: {
      get: function () {
        return this.$store.state.tagsList;
      },
      set: function (newValue) {
        this.$store.commit("TAGES_LIST", newValue);
        // this.$store.state.tagsList = newValue;
      }
    }
  }, methods: {
    //选中的高亮
    isActive(title) {
      console.log(title, title === "icon")
      return title === "icon";
    }
  }
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

    .container {
      min-height: 100%;

      .page {
        width: 100%;
        min-height: 100%;
      }

      .active {
        display: block;
      }

      .hidden {
        //display: none;
      }
    }
  }

  .container_collapse {
    margin-left: 64px;
  }
}
</style>