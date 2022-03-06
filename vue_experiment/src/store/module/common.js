import axios from '../../libs/axios'
import Vue from 'vue'
Vue.use(axios);
// 实例化对象可以直接获取this
let thiz = new Vue();
export default {
  state: {
    CustomerObj:[],
    projectObj:[],
    gatewayObj:[],
    provinceObj:[],
    cityObj:[],
  },
  actions: {

  },
  mutations: {

  },
  getters: {

  },
}
