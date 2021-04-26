<template>
  <div :class="[theme,language]">
    <header>
      头部
    </header>
  </div>
</template>

<script>
export default {
  props: {header: Object},
  computed: {
    test: function () {
      return process.env.VUE_APP_IN_TEST == "true";
    },
    theme: function () {
      return this.$store.state.choosedTheme;
    },
    language: function () {
      return "language-" + this.$store.state.choosedLanguage;
    },
  },
  data: function () {
    return {};
  },
  methods: {},
  mounted() {
  },
  watch: {
    //路由变化时候更新
    $route(newVal, oldVal) {
      newVal, oldVal;
      //console.log(oldVal, '->', newVal)

    },
  },
  components: {},
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
header {
  z-index: 10;
  position: fixed;
  top: 0;
  width: 100%;
  line-height: 100px;
  height: 100px;
  background: rgba(18, 18, 18, .4);
}
</style>
