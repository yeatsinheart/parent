<template>
  <div id="content" className="box-size">
    <!-- 默认满视图，各个单独页面自己图片填充头部位置 -->
    用户哦
    <iframe src="https://www.baidu.com" width="100%" height="100%"></iframe>
  </div>
</template>
<style lang="scss" scoped>
</style>
<script>
export default {
  data(){return {}},
  components: {},
  mounted() {
    let initLanguage =
        localStorage.getItem("choosed-language") ||
        localStorage.getItem("application-default-language") ||
        process.env.VUE_APP_DEFAULT_LANGUAGE;
    this.$languageChange(initLanguage);
  },
  computed: {},
};
</script>