import Vue from 'vue'
import App from './App.vue'
import router from './router'

import iView from 'iview';
//引入工具文件
import util from './libs/util'
//引入axios封装文件
import axiosBag from './libs/axios'
import store from './store'
import axios from 'axios'

import 'iview/dist/styles/iview.css';

Vue.config.productionTip = false
Vue.use(iView);
Vue.use(util);
Vue.use(axiosBag);
Vue.prototype.axios = axios
//判断是否是开发环境(不用改这里了，直接打包就行)
//Vue.prototype.baseUrl =  process.env.NODE_ENV == 'development' ? '/api/' : '';
Vue.prototype.baseUrl = "/api/";
Vue.prototype.url = "http://127.0.0.1:8080";

var userInfo = {
  userId:'',
  userName:'',
  customerName:'',// 宝链人工智能
  projectName:'',//深大计算机学院停车场
  token:12354354352345,
  isAdmin:true
}
localStorage.setItem("userInfo",JSON.stringify(userInfo));

new Vue({
  store,
  axios,
  router,
  render: h => h(App)
}).$mount('#app')
