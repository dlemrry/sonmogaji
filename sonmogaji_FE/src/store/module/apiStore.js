
import axios from 'axios'


const socketStore = {
    namespaced: true,
    state: {
      axios:{}
    },
  
    getters: {
        getAxios(state){
            return state.axios;
        },
    },
  
    mutations: {
        setAxios(state, axios){
            state.axios=axios;
        },
    },
  
    actions: {
      //axios api 요청들
      
      
    },
  };
  
  export default socketStore;