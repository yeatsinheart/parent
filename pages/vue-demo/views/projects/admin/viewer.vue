<template>
  <div id="content" class="box-size">
    <!-- 默认满视图，各个单独页面自己图片填充头部位置 -->
    <router-view/>


        <div style="text-align:center;position:absolute;bottom:20px;right:20px;width:60px;height:60px;background-color:#409EFF;border-radius:50%;">
          <i class="el-icon-chat-dot-square" style="line-height: 60px;font-size: 24px;color: white"></i>
        </div>

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