<template>
  <div class="login-container">
    <div class="first-part">
      <div class="web-title">科学前沿港</div>
      <div class="web-title-en">Science Frontiers Hub</div>
    </div>
    <div class="form">
      <el-form ref="signinForm" :model="signinForm" :rules="signinRules" class="login-form" autocomplete="off"
        label-position="left">

        <div class="title-container">
          <h3 class="title">Signin Form</h3>
        </div>
        <!-- 用户名 -->
        <el-form-item prop="username">
          <span class="svg-container">
            <!-- <svg-icon icon-class="user" /> -->
            用户名
          </span>
          <el-input ref="username" v-model="signinForm.username" name="username" type="text" tabindex="1"
            autocomplete="off" />
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <span class="svg-container">
            <!-- <svg-icon icon-class="password" /> -->
            密码
          </span>
          <el-input :key="passwordType" ref="password" v-model="signinForm.password" :type="passwordType"
            name="password" tabindex="2" autocomplete="off" />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
        <!-- 二次确认密码 -->
        <!-- <el-form-item prop="checkPass" v-if="signinForm.password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input :key="passwordType" :type="passwordType" ref="checkPass" v-model="signinForm.checkPass"
            autocomplete="off" placeholder="再次输入密码"></el-input>
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item> -->
        <!-- 授权码 -->
        <el-form-item prop="role_token">
          <span class="svg-container">
            <!-- <svg-icon icon-class="user" /> -->
            授权码
          </span>
          <el-input type="text" ref="role_token" v-model="signinForm.role_token" autocomplete="off" name="role_token"
            tabindex="3">
          </el-input>
        </el-form-item>
        <el-button :loading="loading" style="width:100%;margin-bottom:30px;background: #696969; color: #fff;"
          @click.native.prevent="handleSignin">Signin</el-button>

        <div class="tips">
          <span>已有账号，</span>
          <span class="blue-text-underline" @click="goLogin">请登录</span>
        </div>

      </el-form>
    </div>
  </div>
</template>

<script>
  import { validRoleToken } from '@/utils/validate'
  import axios from 'axios';

  export default {
    name: 'Signin',
    data ()
    {
      const validateUsername = (rule, value, callback) =>
      {
        if (!value) {
          callback(new Error('请输入用户名'))
        } else {
          callback()
        }
      }
      const validatePassword = (rule, value, callback) =>
      {
        if (value.length < 6) {
          callback(new Error('请输入密码长度不少于六位'))
        } else {
          callback()
        }
      }
      const validateRoleToken = (rule, value, callback) =>
      {
        if (!validRoleToken(value)) {
          callback(new Error('请输入正确授权码'))
        } else {
          callback()
        }
      }

      return {
        signinForm: {
          username: '',
          password: '',
          role_token: '',
        },
        signinRules: {
          username: [{ required: true, trigger: 'blur', validator: validateUsername }],
          password: [{ required: true, trigger: 'blur', validator: validatePassword }],
          role_token: [{ required: true, trigger: 'blur', validator: validateRoleToken }]
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
      sigin ()
      {
        const params = {
          username: this.signinForm.username,
          password: this.signinForm.password,
          role_token: this.signinForm.role_token,
        }
        this.$store.dispatch('user/signin', params)
          .then(response =>
          {
            // 处理响应数据
            this.loading = false
            if (!this.loading) {
              this.handleLogin()
            }
          })
          .catch(error =>
          {
            // 处理错误情况
            console.error(error);
            this.loading = false
            // 注册失败，请稍后再试
            this.$message.error('注册失败，请稍后再试');
            // console.log('error submit!!')
            return false
          });
        // await ;
      },
      handleSignin ()
      {
        this.$refs.signinForm.validate(valid =>
        {
          if (valid) {
            this.loading = true
            this.sigin();
          }
        })
      },
      handleLogin ()
      {
        this.$refs.signinForm.validate(valid =>
        {
          if (valid) {
            this.loading = true
            this.$store.dispatch('user/login', this.signinForm).then(() =>
            {
              this.$router.push({ path: this.redirect || '/' })
              this.loading = false
              this.$message({
                message: '登录成功！',
                type: 'success'
              });
            }).catch(() =>
            {
              this.loading = false
            })
          } else {
            // console.log('error submit!!')
            this.$message.error('登录失败，请稍后再试');
            return false
          }
        })
      },
      // 跳转至登录页面
      goLogin ()
      {
        this.$router.push('/login')
      }
    }
  }
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg: #e5e5e5;
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
      padding: 0 35px;
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
      /* width: 30px; */
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