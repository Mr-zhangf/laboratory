import Vue from 'vue'
import Vuex from 'vuex'
import user from './module/user'
import common from './module/common'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //
  },
  mutations: {
    //
  },
  actions: {
    //
  },
  modules: {
    user,
    common
  }
});
