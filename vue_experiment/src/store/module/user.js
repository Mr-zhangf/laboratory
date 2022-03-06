// import axios from '../libs/axios';
// // 引入qs模块，用来序列化post类型的数据
// import QS from 'qs'

export default {
  state: {
    userInfo:{

    },
    text:1000,
    count:1,
  },
  actions: {
    // 登录后保存用户信息
    saveUserInfo ({ state,commit }, userInfo) {
      commit("saveInfo",userInfo);
    },
  },
  mutations: {
    saveInfo (state, data) {
      state.userInfo = data
    }
  },
  getters: {

  },
}
