<!-- 顶部信息栏 -->
<!-- 顶部信息栏 -->
<!-- 顶部信息栏 -->
<template>
  <header id="topbar">
    <el-row>
      <el-col :span="4" class="topbar-left">
        <h3></h3>
        <span class="title" @click="index()"></span>
      </el-col>
      <el-col :span="20" class="topbar-right">
        <i class=""></i>
        <div class="user">
          <span>{{user.userName}}</span>
          <img src="@/assets/img/userimg.png" class="user-img" ref="img" @click="showSetting()" />
          <a href=""></a>
          <a @click="exit()">退出登录</a>
          <transition name="fade">
            <div class="out" ref="out" v-show="login_flag">
              <ul>
                <li><a @click="manage">用户信息</a></li>
                <li><a href="javascript:;">设置</a></li>
              </ul>
            </div>
          </transition>
        </div>
      </el-col>
    </el-row>
  </header>
</template>

<script>
import store from '@/vuex/store'
import {mapState,mapMutations} from 'vuex'
import * as auth from '../../utiles/auth'
export default {
  data() {
    return {
      login_flag: false,
      user: { //用户信息
        userName: null,
        userId: null
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  computed: mapState(["flag","menu"]),
  methods: {
    //显示、隐藏退出按钮
    showSetting() {
      this.login_flag = !this.login_flag
    },
    manage() {  //跳转到修改密码页面
      if (this.$cookies.get("role")==1){
        this.$router.push({path: '/manager'})
      }else if (this.$cookies.get("role")==0){
        this.$router.push({path: '/manager2'})
      }

    },
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
    index() {
      this.$router.push({path: '/index'})
    },
    exit() {
      let role = this.$cookies.get("role")
      this.$cookies.remove("cname") //清除cookie
      this.$cookies.remove("cid")
      this.$cookies.remove("role")
      console.log("header"+auth.getAdminInfo())
      auth.removeAdminInfo()
      this.$router.push({path:"/"}) //跳转到登录页面
      if(role == 0) {
        this.menu.pop()
      }
    }
  },
  store
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
#topbar {
  position: relative;
  z-index: 10;
  background-color: white;
  height: 80px;
  line-height: 80px;
  color: black;
  box-shadow: 5px 0px 10px rgba(0, 0, 0, 0.5);
}
#topbar .topbar-left {
  height: 80px;
  display: flex;
  justify-content: center;
  background: white;
  overflow: hidden;
}
.topbar-left .icon-kaoshi {
  font-size: 60px;
}
.topbar-left .title {
  font-size: 20px;
  cursor: pointer;
}
.topbar-right {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.topbar-right .user-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.topbar-right .el-icon-menu {
  font-size: 30px;
  margin-left: 20px;
}
.topbar-right .user {
  position: relative;
  margin-right: 40px;
  display: flex;
}
.topbar-right .user .user-img {
  margin-top: 15px;
  margin-left: 10px;
  cursor: pointer;
}
.user .out {
  font-size: 14px;
  position: absolute;
  top: 80px;
  right: 0px;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  padding: 12px;
}
.user .out ul {
  list-style: none;
}
.user .out ul > li {
  height: 26px;
  line-height: 26px;
}
.out a {
  text-decoration: none;
  color: #000;
}
.out .exit {
  margin-top: 4px;
  padding-top: 4px;
  border-top: 1px solid #ccc;
}
</style>
