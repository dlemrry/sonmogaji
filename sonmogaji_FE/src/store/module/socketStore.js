// import Stomp from "webstomp-client";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const socketStore = {
  state: {
    socket: {},
    stomp: {},
    chatmessages:[],
    memorandumState:{},
  },

  getters: {
    getStomp(state) {
      return state.stomp;
    },
    getSocket(state) {
      return state.socket;
    },
    getChatmessages(state){
      return state.chatmessages
    },
    getMemorandumState(state){
      return state.memorandumState
    },
  },

  mutations: {
    setStomp(state, stomp) {
      state.stomp = stomp;
    },
    setSocket(state, socket) {
      state.socket = socket;
    },
    setChatmessages(state,messages){
      state.chatmessages=messages;
    },
    receiveChatmessages(state, message){
      state.chatmessages.push(message);
    },
    setMemorandumState(state, memorandumstate){
      state.memorandumState=memorandumstate;
    }
  },

  actions: {
    //stomp socket 요청들
    //socket 연결들

    memorandumStart({ commit, state }) {
      state.stomp.send(
        "/memorandum",
        {},
        JSON.stringify({
          type: "start",
        })
      );
    },
    memorandumVote({ commit, state ,payload}) {
      state.stomp.send(
        "/memorandum",
        {},
        JSON.stringify({
          type: "vote",
        })
      );
    },
    chat({ commit, state ,payload}) {
      state.stomp.send(
        "/chat",
        {},
        JSON.stringify({
          type: "send",
          chatmessage: payload
        })
      );
    },
    clearChat({ commit}) {
      commit("setChatmessages", []);
    },

    stompConnect({ commit, state }) {
      const serverURL = "http://localhost:8080/room";

      // let socket = new SockJS(serverURL);
      commit("setSocket", new SockJS(serverURL));
      console.log(state.socket);
      // let stompClient = Stomp.over(state.socket);

      commit("setStomp", Stomp.over(state.socket));
      console.log(state.stomp);
      state.stomp.connect(
        {},
        () => {
          // 소켓 연결 성공
          state.stomp.connected = true;
          console.log("소켓 연결 성공");
          commit("setStomp", state.stomp);

          state.stomp.subscribe("/memorandum", (res) => {
            var content = JSON.parse(res.body);
            let type = content.type;
            switch (type) {
              case "join":
                break;

              case "vote":
                break;
              default:
                break;
            }
          });

          state.stomp.subscribe("/chat", (res) => {
            var content = JSON.parse(res.body);
            let type = content.type;
            switch (type) {
              case "join":
                break;

              case "send":
                commit("receiveChatmessages", content.message);
                break;
              default:
                break;
            }
          });
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
