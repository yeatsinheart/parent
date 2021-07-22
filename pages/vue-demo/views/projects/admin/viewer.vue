<template>
  <div id="content" class="box-size">
    <!-- 默认满视图，各个单独页面自己图片填充头部位置 -->
    <router-view/>


  </div>
</template>
<style lang="scss" scoped>
</style>
<script>
export default {
  data() {
    return {}
  },
  components: {},
  mounted() {
    /*let initLanguage =
        localStorage.getItem("language") ||
        localStorage.getItem("default-language") ||
        process.env.VUE_APP_LANGUAGE;
    this.$languageChange(initLanguage);*/
  },
  computed: {},
};
</script>