// import Stomp from "webstomp-client";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const socketStore = {
  state: {
    roomId:"",
    nickname:"",
    socket: {},
    stomp: {},
    chatmessages: [],
    memorandumState: {},
  },

  getters: {
    getRoomId(state){
      return state.stomp
    },
    getNickname(state){
      return state.nickname
    },
    getStomp(state) {
      return state.stomp;
    },
    getSocket(state) {
      return state.socket;
    },
    getChatmessages(state) {
      return state.chatmessages;
    },
    getMemorandumState(state) {
      return state.memorandumState;
    },
  },

  mutations: {
    setRoomId(state,roomId){
      state.roomId=roomId
    },
    setNickname(state,nickname){
      state.nickname=nickname
    },
    setStomp(state, stomp) {
      state.stomp = stomp;
    },
    setSocket(state, socket) {
      state.socket = socket;
    },
    setChatmessages(state, messages) {
      state.chatmessages = messages;
    },
    receiveChatmessages(state, message) {
      state.chatmessages.push(message);
    },
    setMemorandumState(state, memorandumstate) {
      state.memorandumState = memorandumstate;
    },
  },

  actions: {
    //stomp socket 요청들
    //socket 연결들

    roomCreate({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/create",
        {},
        JSON.stringify({
          roomId:state.roomId,
        })
      );
    },
    
    roomJoin({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/join",
        {},
        JSON.stringify({
          roomId: state.roomId,
          senderNickname:state.nickname
        })
      );
    },
    roomStart({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/start",
        {},
        JSON.stringify({
          roomId:state.roomId,
        })
      );
    },
    roomVote({ payload, commit, state }) {
      state.stomp.send(
        "/pub/memorandum/action",
        {},
        JSON.stringify({
          roomId:state.roomId,
        })
      );
    },
    chat({ message, commit, state }) {
      state.stomp.send(
        "/pub/chat/message",
        {},
        JSON.stringify({
          sender: state.nickname,
          message: message,
          roomId:state.roomId,
        })
      );
    },
    clearChat({ commit }) {
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

          state.stomp.subscribe("sub/memorandum/join"+state.roomId, (res) => {
            var content = JSON.parse(res.body);
            commit("setRoomId",content.roomId)
            commit("setChatmessages", content.chatLog);

          });
          state.stomp.subscribe("sub/memorandum/action"+state.roomId, (res) => {
            var content = JSON.parse(res.body);
          });

          state.stomp.subscribe("sub/chat/send"+state.roomId, (res) => {
            var content = JSON.parse(res.body);

            commit("receiveChatmessages", { sender: content.sender, message: content.message });
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
