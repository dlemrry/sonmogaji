import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import apiStore from "./module/apiStore";

import socketStore from './module/socketStore';
Vue.use(Vuex)
const store = new Vuex.Store({
  modules: {
    apiStore,
    socketStore
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;