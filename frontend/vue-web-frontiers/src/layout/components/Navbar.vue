<template>
  <div class="navbar">
    <!-- todo 网站logo替换 -->
    <hamburger :is-active="sidebar.opened" class="hamburger-container" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <div class="user-actions">
        <!-- <div class="sign-in-action" @click="handlSignIn">注册</div> -->
        <div v-if="!hasToken" class="log-in-action" @click="handleLogIn">登录{{hasToken}}</div>
        <div v-else>欢迎：{{username}}</div>
      </div>
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/userManage">
            <el-dropdown-item>
              系统管理
            </el-dropdown-item>
          </router-link>
          <!-- <a target="_blank" href="https://github.com/PanJiaChen/vue-admin-template/">
            <el-dropdown-item>Github</el-dropdown-item>
          </a>
          <a target="_blank" href="https://panjiachen.github.io/vue-element-admin-site/#/">
            <el-dropdown-item>Docs</el-dropdown-item>
          </a> -->
          <el-dropdown-item divided @click.native="logout" v-if="hasToken">
            <span style="display:block;">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>

    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Breadcrumb from '@/components/Breadcrumb'
  import Hamburger from '@/components/Hamburger'
  import { getToken, removeToken, getUsername, removeUsername } from "@/utils/auth"; // get token from cookie

  export default {
    components: {
      Breadcrumb,
      Hamburger
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'avatar'
      ]),
      hasToken ()
      {
        // determine whether the user has logged in
        const token = getToken();
        return token
      },
      username ()
      {
        const name = getUsername();
        return name;
      },
      avatar ()
      {
        const src = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        return src;
      }
    },
    methods: {
      // toggleSideBar()
      // {
      //   this.$store.dispatch('app/toggleSideBar')
      // },
      async logout ()
      {
        // await this.$store.dispatch('user/logout')
        removeToken();
        removeUsername();
        this.$router.push(`/login`)
        location.reload();
      },



      // 点击跳转至登录页面
      handleLogIn ()
      {
        this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .navbar {
    height: 50px;
    overflow: hidden;
    position: relative;
    /* background: #fff; */
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
    background-color: #696969;
    color: #fff;

    .hamburger-container {
      line-height: 46px;
      height: 100%;
      float: left;
      /* cursor: pointer; */
      transition: background .3s;
      -webkit-tap-highlight-color: transparent;

      /* &:hover {
        background: rgba(0, 0, 0, .025)
      } */
    }

    .breadcrumb-container {
      float: left;
    }

    .right-menu {
      display: flex;
      float: right;
      height: 100%;
      line-height: 50px;

      &:focus {
        outline: none;
      }

      .right-menu-item {
        display: inline-block;
        padding: 0 8px;
        height: 100%;
        font-size: 18px;
        color: #5a5e66;
        vertical-align: text-bottom;

        &.hover-effect {
          cursor: pointer;
          transition: background .3s;

          &:hover {
            background: rgba(0, 0, 0, .025)
          }
        }
      }

      .user-actions {
        margin-right: 30px;
        display: flex;

        .sign-in-action {
          margin-right: 12px;
          cursor: pointer;
        }

        .log-in-action {
          cursor: pointer;
        }
      }

      .avatar-container {
        margin-right: 30px;

        .avatar-wrapper {
          margin-top: 5px;
          position: relative;

          .user-avatar {
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }

          .el-icon-caret-bottom {
            cursor: pointer;
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
          }
        }
      }
    }
  }
</style>