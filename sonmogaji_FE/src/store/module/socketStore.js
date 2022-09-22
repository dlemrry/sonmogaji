import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

const socketStore = {
  state: {
    socket: {},
    stomp: {},
  },

  getters: {
    getStomp(state) {
      return state.stomp;
    },
    getSocket(state) {
      return state.socket;
    },
  },

  mutations: {
    setStomp(state, stomp) {
      state.stomp = stomp;
    },
    setSocket(state, socket) {
      state.socket = socket;
    },
  },

  actions: {
    //stomp socket 요청들
    //socket 연결들

    stompConnect({ commit, state }) {
      const serverURL = "http://localhost:8080";
      let socket = new SockJS(serverURL);
      let stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

      stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          stompClient.connected = true;
          console.log("소켓 연결 성공", frame);
          commit("setStomp", stompClient);

          state.stomp.subscribe("/send", (res) => {});
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
        }
      );
    },
  },
};

export default socketStore;
