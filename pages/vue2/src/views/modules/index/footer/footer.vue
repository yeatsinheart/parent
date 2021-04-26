<template>
  <div :class="[theme,language]" v-if="canShow()">
    <footer>
      <div class="container ">
        <router-link to="/">
          <div class="box-size">
            <i class="icon icon-home"></i>
            <span>{{ $t('layout-Footer.home') }}</span>
          </div>
        </router-link>
        <router-link to="found">
          <i class="icon icon-home"></i>
          <span>{{ $t('layout-Footer.found') }}</span>
        </router-link>
        <router-link to="welfare">
          <i class="icon icon-home"></i>
          <span>{{ $t('layout-Footer.welfare') }}</span>
        </router-link>
        <router-link to="user">
          <i class="icon icon-home"></i>
          <span>{{ $t('layout-Footer.myself') }}</span>
        </router-link>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'Footer',
  data(){return {}},
  methods: {
    canShow(){
      return (this.$route.meta && this.$route.meta.withFooter && this.$route.meta.withFooter===true)
    }
  },
  computed: {
    test: function () {
      return process.env.VUE_APP_IN_TEST == 'true'
    }, theme: function () {
      return this.$store.state.choosedTheme
    }, language: function () {
      return 'language-' + this.$store.state.choosedLanguage
    }
  }, watch: {
    //路由变化时候更新
    $route(newVal, oldVal) {
      newVal === oldVal
      //console.log(newVal, oldVal);
      /*if (newVal.meta.index >= 0) {
          this.tabIndex = newVal.meta.index;
          localStorage.setItem("tabIndex", this.tabIndex);
      }*/
    }
  }
}
export const langMsg = {
  'zh-CN': {
    'layout-Footer': {
      'home': '欢迎光临',
      'found': '中奖信息',
      'welfare': '福利中心',
      'myself': '个人中心',
      'language': '语言设置',
      'theme': '主题设置',
      'router': '页面路由'
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
$tmp: (
    default:(
        homeIcon:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAsCAMAAAApWqozAAAAWlBMVEUAAABMSUlsY1xrZFxtY1xtY1xrYlxtZFxsY1xoVVNtY11qYVtrYFlsY11qYlpsY1xjYVZtY1xsY1xsY1xsZFxsY11sYlxrXldtZFxtZFxsY1xsYFtqY1xtZF2fSIsiAAAAHXRSTlMABtCFm7dq990L8jgoy0GUFNXgw31zXyam62UtSGCxpZcAAADbSURBVDjL1dTbrsIgEIVhoHu3CHLq2cN6/9fUaENqY6djoon+N3DxXUwIGfFdWe+tyHlPWakAJ+/32DdA00cGHgqNW7oYKHw9/GiQM6OXqzgeGyxqjvE5NjWeVBf7Jd4lrGbG83yaWDqQuTJPE2psVocJV2BUTdhwsJlw+GMUxE8nwz9RkI84gSgtsKKwWsPVrE3cDvupod3EpciV78cna09cLB3gJBcrQLFxAhIXi77r+o+8Bh93u1y3iQ86dyAw+9cJGs+gtdZrCmt/Ja8vGT5+fde1BaNWfLILO25XznW2Q7AAAAAASUVORK5CYII=),
        homeIconActive:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAsCAMAAAApWqozAAAAZlBMVEUAAACVHR2uJiauJyeuJyeuJyeuJiauJyeuJyemFxevJyetJiasJSWtJiavKCivJyeuJyeuJyepISGvKCivJyetJyeuJyeuIiKuJyevJyevJyeuKCiuKCitJyeuJiaqKCiuJyevKCjsW97NAAAAIXRSTlMABoXQm7dr3fcLyzgoQfXvlWcU1eB9Xyam68XBunZxLUjCR1a4AAAA4klEQVQ4y9XU224CIRCAYdhtBUHOe/Lcef+XbGsI6kZgNJrofzfJl8xcDXmvpFIyDVQpWrC0BRAR+GELsB08Ao8Ng1OsGUv4f/9kIGUmRbPYuw3M2jh/G5sObtQ1qzleBsimp5/La7wTUEycr7EdVOtsxBwQ8Yg1BuuI7RciSz46anff2XaWXuMAhcIMtyXc5jDXPKZ5FffjKjb2VexIylXxgqQWKHyU8ojFVAAI+hIcAAIaD/v9gMSxp+PDMnWo4jVLrfM4W8KpMr6AUkrFSpipPxKxAUTmLvzI++obRD15Zb/fH2OFtSCOjAAAAABJRU5ErkJggg==),
    )
);
footer {
  z-index: 99;
  width: 100%;
  position: fixed;
  bottom:0;
  @include getThemeSelector() {
    background: getContent("secondBGColor");
  }
}

.container {
  margin: 0 auto !important;
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center;     /* 垂直居中 */
}

footer a {
  display: inline-block;
  width: 1rem;
  //margin-top: .35rem;
  text-align: center;
  @include getThemeSelector() {
    color: getContent("primaryFontColor");
  }

  &.router-link-exact-active {
    @include getThemeSelector() {
      background: getContent("secondBGColor");
    }
    @include getThemeSelector() {
      color: getContent("primaryFontColor");
    }
    border-radius: $borderRadius $borderRadius 0 0;
  }
}

.icon {
  display: block;
  width: .235rem;
  height: .235rem;
  margin: 0 auto !important;
  background-repeat: no-repeat;
  background-size: 100% 100%;

  &.icon-home {
    @include getThemeSelector($tmp) {
      background-image: getContent("homeIcon");
    }

    a.router-link-exact-active & {
      @include getThemeSelector($tmp) {
        background-image: getContent("homeIconActive");
      }
    }
  }
}

</style>
