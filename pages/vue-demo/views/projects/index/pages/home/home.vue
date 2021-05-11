<template>
  <div id="content" class="box-size">
    <!-- 默认满视图，各个单独页面自己图片填充头部位置 -->
    首页哦
    {{ $t('home.msg') }}
  </div>
</template>
<style lang="scss" scoped>
</style>
<script>
export const langMsg = {
  'zh-CN': {
    'home': {
      'msg': '欢迎光临'
    }
  }
}
export default {
  data() {
    return {}
  },
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