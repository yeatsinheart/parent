<template>
  <div v-if="canShow()" :class="[theme,language]">
    <header>
      <div class="middlebar" style="min-width: 941px">
        <div class="middlebar-inner">
          <div class="middlebar-left"><a class="logo-link" href="/">
            <img class="logo"
                 src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANgAAAA2CAMAAACSsKctAAAANlBMVEVHcEzuQEDwRETyRUX3TEzvQkLuQEDuQUHuQUHvQEDuQUHuQEDvQkLuQEDuQUHwQUHxQkLtQEDKaksGAAAAEXRSTlMAxiweDlyo49Nri/F5+bhLOapXaIgAAATMSURBVGje7ZrpsqQqDIAb2VEE3/9lx263AAmu3XVv1cn8mFOtIh+JIQuvFyFSzdK8/jsSrbg7BG+HSdjPZi3iJPTchR7a7iYam7mGNvwKTM5vlOQd6n3Z37IhN6zSNhW1TsJqMt/TwQk6G3gxmt0DC5/LfpxS9U38wOJ9RJN38uG4KGBR0wRNxrcHtn4dlPjpv4pCYzqEEffBfAG2XDC9sxyCUbbfH3wVDdbkS2P4FzQGRR4As8NdsEYXy01Y4xMa28C6KljT3gUDXNtYOn5fY3UwNtwEA9+XA397+zONxR03fQ1MerjG0Is4QYK5riLzEMDdC4+CuQoY99sKoW9R20z5zsKwN0gAczDFUohdPzSKRpw4j9Yp0+Jg2GgCrHBf3sAVuIytC8u4RjIwYmGOh8AMvTutfLtgydR8ZjzCgeV32M7eFlyZj2T8GY3lfHwNmChTGmcP5tECNNFBf4m4AqEGhGt8rYEj2i+AwUhwFCocgOs+ok383MFfMecdoGYUHFwkW76Jz5kiHlmQ4TtPY48+iNAnfqgX1a+vtNMueXyjvqox5WTkJFgthq3t0i2ydhY+4MsbYhKMtEu6dxFseqw1Gd+s2raasylPRgCluoJJgnnU3SYaHbS8Y4rJBv3h42AFdN1gGzwWZtgcMHD3Xk5BfYOz67qlsWKDnq3G1DP74DTCZVwQVbA1X57WpWWqk40od4svgInKBrtUPJSpfGPGyYYC6zma/OjJm0RzBcwcBONltAzKIVYxfyBObJlbFAHAtKSyOla4x+c1FiuhAxvOiDcpWBKo4GDbp3sLDA2CJR07EGCaUZYJwUys5OGgBhdYCRYFIWQQHKzrjU/AXCUdQ8C0e0+Y295XwbSsFhiS4qLUC9hePsZ2QqqVT4I3NvtgvrfbXQLxkwsYUoWsgI37uX4GbOXj231+J332DPHsXKb+cknCkC2gkZ1iLQE2RtRPgiVJq6mAadVtUKL5CAea69v9qGxxs/KTMLFrVaozYLLi7UcwrVxWY3XYtjfu32q07cOF/Ob7YKpWLm0Emewj+7m43Ud5EGzdAvjJKnz/jTbCg2B2OBIpXgBzZl9a/xFdhBChwYWXYIEYe1GYzn6fHlPIE3rJDhBBTPxIPHYng5bDObEXQqrEK34PTP+B/c/A/kzxD+wUWLB1AXVJd8cUm3Jkv1W/SpmGlsiVOUw32GxPbMVbZtA9WwneGjo+Xun/34s8hEGLqQ+AgUaVff0cDHCpJ7stSZvKTVW//vNPoXVuNV3rVQ7WVR+juRjRfLqtMeuzkWOtG2SxdtrnuehrSc9utyBpwjwB5sp23eqdI33Og5Wm6IZan3eXKz/wcc8UYdl87ZOsrUtDZmzz6xIwoU9nB2CHKw5FLGBWVkQXzrTsu4Fvw1K+JGRWmjoPiTjto6eqysMeF08NFA0chRVu8jNRyzpogXpF6rF9LuSw2MVzHtnphMxRNLgj6IasdpCBNef8B+CKj53zyI5tWOqdATsP0VP7GPoYJd1QDQuuagw2f30xj9URaFF6MM8pMPSxfa7w4FmqxtTb4YgjCOVPReQRanvgKa5TGuvQ7j9+rq5QD6aNMqRa/LffKb9JvQoR6Al9XCQyrCZCIL5eX5Z4+QEscJfekj/2D7KhSDEKe9jHAAAAAElFTkSuQmCC"></a>
          </div>
          <div class="middlebar-right">
            <ul class="tab">
              <li class="tab-item"><a href="//www.toutiao.com/about/">关于头条</a></li>
              <li class="tab-item"><a href="//job.toutiao.com" target="_blank">加入头条</a></li>
              <li class="tab-item"><a href="//www.toutiao.com/report/">媒体报道</a></li>
              <li class="tab-item"><a href="//www.toutiao.com/cooperation/">产品合作</a></li>
              <li class="tab-item tab-item-active"><a href="//www.toutiao.com/media_cooperation/">合作说明</a></li>
              <li class="tab-item"><a href="//www.toutiao.com/contact/">联系我们</a></li>
              <li class="tab-item"><a href="//www.toutiao.com/user_agreement/">用户协议</a></li>
              <li class="tab-item"><a href="//www.toutiao.com/complain/">投诉指引</a></li>
              <li class="tab-item"><a href="//www.toutiao.com/friend_link/">友情链接</a></li>
            </ul>
          </div>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
export default {
  props: {header: Object},
  computed: {
    test: function () {
      return process.env.VUE_APP_IS_DEV == "true";
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
  methods: {
    canShow() {
      return (this.$route.meta && this.$route.meta.withHeader && this.$route.meta.withHeader === true)
    }
  },
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
  background: #fff;
  margin-bottom: 16px;

  .topbar {
    height: 34px;
    line-height: 34px;
    background: #222;
    font-size: 14px;
    color: #fff;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    -moz-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -ms-flex-wrap: wrap-reverse;
    flex-wrap: wrap-reverse;

    .right-nav {
      li, ol, ul {
        list-style: none;
      }

      .user-nav-list {
        .tb-item {
          position: relative;
          float: left;
          border-left: 1px solid #3a3a3a;

          &:first-child {
            border: none;
          }
        }
      }
    }
  }

  .middlebar {
    width: 100%;
    height: 58px;
    background: #fff;
    -webkit-box-shadow: 0 2px 16px rgba(54, 56, 79, 6%);
    box-shadow: 0 2px 16px rgba(54, 56, 79, 6%);

    .middlebar-inner {
      margin: 0 auto;
      display: -moz-box;
      display: -ms-flexbox;
      display: flex;
      -moz-box-pack: justify;
      -ms-flex-pack: justify;
      justify-content: space-between;
      -moz-box-align: center;
      -ms-flex-align: center;
      align-items: center;

      .middlebar-left {
        line-height: 58px;

        .logo-link > img {
          width: 108px;
          height: 27px;
          vertical-align: middle;
        }
      }

      .tab .tab-item {
        margin-left: 28px;
        display: inline-block;
        font-size: 16px;
        line-height: 56px;
        border-bottom: 2px solid transparent;

        .tab-item-active {
          border-bottom: 2px solid #f85959;
        }

        a {
          color: #444;
          cursor: pointer;
          background-color: transparent;

          &:hover {
            text-decoration: none;
          }
        }
      }

      li {
        list-style: none;
      }
    }
  }

}
</style>
