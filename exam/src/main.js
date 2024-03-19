// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import echarts from 'echarts'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueCookies from 'vue-cookies'
import * as auth from './utiles/auth'

Vue.use(ElementUI)
Vue.use(VueCookies)

Vue.config.productionTip = false
Vue.prototype.bus = new Vue()
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios//axios变量挂载到vue身上，相当于vue有了这个属性，在任意组件就不需要在导入axios了，用this.$axios代替，也还可以设置默认的baseURL



//这个方法需要放在new Vue之前，不然按F5刷新页面不会调用这个方法
router.beforeEach(function (to, from, next) {
  if (to.meta.needLogin) {
    if (auth.getAdminInfo()) {
      console.log('有cookie信息')
      next();
    }else {
      console.log('无cookie信息')
      next({
        path:'/'
      });
    }
  }else{
    next();
  }
})


new Vue({
  el: '#app',
  router,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
