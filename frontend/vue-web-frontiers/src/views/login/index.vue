<template>
  <div class="login-container">
    <div class="first-part">
      <div class="web-title">科学前沿港</div>
      <div class="web-title-en">Science Frontiers Hub</div>
    </div>
    <div class="form">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on"
        label-position="left">

        <div class="title-container">
          <h3 class="title">Login Form</h3>
        </div>

        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input ref="username" v-model="loginForm.username" placeholder="Username" name="username" type="text"
            tabindex="1" auto-complete="on" />
        </el-form-item>

        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType"
            placeholder="Password" name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>

        <el-button :loading="loading" style="width:100%;margin-bottom:30px; background: #696969; color: #fff;"
          @click.native.prevent="handleLogin">Login</el-button>

        <div class="tips">
          <span>专家学者</span>
          <span class="blue-text-underline" @click="goSignin">注册入口</span>
        </div>

      </el-form>
    </div>

  </div>
</template>

<script>
  import { validUsername } from '@/utils/validate'

  export default {
    name: 'Login',
    data ()
    {
      const validateUsername = (rule, value, callback) =>
      {
        if (!validUsername(value)) {
          callback(new Error('Please enter the correct user name'))
        } else {
          callback()
        }
      }
      const validatePassword = (rule, value, callback) =>
      {
        if (value.length < 6) {
          callback(new Error('The password can not be less than 6 digits'))
        } else {
          callback()
        }
      }
      return {
        loginForm: {
          username: '',
          password: ''
        },
        loginRules: {
          username: [{ required: true, trigger: 'blur', validator: validateUsername }],
          password: [{ required: true, trigger: 'blur', validator: validatePassword }]
        },
        loading: false,
        passwordType: 'password',
        redirect: undefined
      }
    },
    watch: {
      $route: {
        handler: function (route)
        {
          this.redirect = route.query && route.query.redirect
        },
        immediate: true
      }
    },
    methods: {
      showPwd ()
      {
        if (this.passwordType === 'password') {
          this.passwordType = ''
        } else {
          this.passwordType = 'password'
        }
        this.$nextTick(() =>
        {
          this.$refs.password.focus()
        })
      },
      handleLogin ()
      {
        this.$refs.loginForm.validate(valid =>
        {
          if (valid) {
            this.loading = true
            this.$store.dispatch('user/login', this.loginForm).then(() =>
            {
              this.$router.push({ path: this.redirect || '/' })
              this.loading = false
            }).catch(() =>
            {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },

      // 点击跳转至注册页面
      goSignin ()
      {
        this.$router.push('/signin')
      },
    }
  }
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg: #283443;
  $light_gray: #696969;
  $cursor: #696969;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }

  .login-container::after {
    content: "";
    /* 使伪元素为空 */
    display: block;
    /* 转换为块级元素 */
    position: absolute;
    /* 绝对定位 */
    bottom: 0;
    /* 位于底部 */
    width: 100%;
    /* 宽度与父元素相同 */
    height: 50px;
    /* 高度设置为你想要的值 */
    background-color: #327442;
    /* 背景颜色设置为你想要的颜色 */
    z-index: -1;
    /* 确保它在内容下方 */
  }
</style>

<style lang="scss" scoped>
  $bg: #eee;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    /* background-color: $bg; */
    overflow: hidden;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 60px 35px;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #696969;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .first-part {
      display: flex;
      height: 160px;
      padding: 100px 20px 12px 20px;
      background-color: #327442;
      margin-bottom: 18px;

      .web-title {
        font-size: 40px;
        color: white;
        font-weight: 800;
      }

      .web-title-en {
        margin-left: 18px;
        font-size: 32px;
        color: #dcd8a3;
        font-weight: 400;
        align-content: end;
        padding-left: 10px;
        border-left-style: solid;
      }
    }

    .form {
      /* background: #c9e0bf; */
      margin: 40px 60px;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: #696969;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .tips {
      font-size: 14px;
      color: #696969;
      margin-bottom: 10px;

      .blue-text-underline {
        color: #409EFF;
        /* 设置字体颜色为蓝色 */
        text-decoration: underline;
        /* 添加下划线 */
        cursor: pointer;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>