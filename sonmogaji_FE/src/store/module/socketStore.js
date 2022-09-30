// import Stomp from "webstomp-client";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const socketStore = {
  state: {
    roomCode: "",
    senderNickName: "",
    roll: "",
    socket: {},
    stomp: {},
    chatmessages: [],
    memorandumState: {},
  },

  getters: {
    getRoomCode(state) {
      return state.roomCode;
    },
    getSenderNickName(state) {
      return state.senderNickName;
    },
    getRoll(state) {
      return state.roll;
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
    setRoomCode(state, roomCode) {
      state.roomCode = roomCode;
    },
    setSenderNickName(state, senderNickName) {
      state.senderNickName = senderNickName;
    },
    setRoll(state, roll) {
      state.roll = roll;
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

    enterNickName({ commit, state }, senderNickName) {
      commit("setSenderNickName", senderNickName);
    },
    enterRoll({ commit, state }, roll) {
      commit("setRoll", roll);
    },
    enterRoomCode({ commit, state }, roomCode) {
      commit("setRoomCode", roomCode);
    },
    roomCreate({ commit, state }) {
      // state.stomp.send(
      //   "/pub/memorandum/create",
      //   {},
      //   JSON.stringify({
      //     senderNickName: state.senderNickName
      //   })
      // );
    },

    // roomJoin({ commit, state },roomCode) {
    //   state.stomp.send(
    //     "/pub/memorandum/join",
    //     {},
    //     JSON.stringify({
    //       roomCode: roomCode,
    //       senderNickname:state.nickname
    //     })
    //   );
    //   commit("setRoomCode", roomCode)
    // },
    roomStart({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/start",
        {},
        JSON.stringify({
          roomCode: state.roomCode,
        })
      );
    },
    roomVote({ commit, state }, payload) {
      state.stomp.send(
        "/pub/memorandum/action",
        {},
        JSON.stringify({
          roomCode: state.roomCode,
        })
      );
    },
    chat({ commit, state }, message) {
      console.log(state.senderNickName + ": " + message);
      state.stomp.send(
        "/pub/chat/message",
        {},
        JSON.stringify({
          sender: state.senderNickName,
          message: message,
          roomCode: state.roomCode,
        })
      );
    },
    clearChat({ commit }) {
      commit("setChatmessages", []);
    },

    stompConnect({ commit, state }) {
      // const serverURL = "https://j7a308.p.ssafy.io/room";
      const serverURL = "http://localhost:8080/room";
      commit("setSocket", new SockJS(serverURL));
      console.log(state.socket);
      console.log("param nick : " + state.senderNickName);

      commit("setStomp", Stomp.over(state.socket));
      console.log(state.stomp);
      state.stomp.connect(
        {},
        () => {
          state.stomp.connected = true;

          commit("setStomp", state.stomp);
          commit("setChatmessages", [{}, {}]);
          console.log(state.chatmessages);
          console.log("소켓 연결 성공" + state.senderNickName + " " + state.roomCode);
          state.stomp.send(
            "/pub/memorandum/join",
            {},
            JSON.stringify({
              roomCode: state.roomCode,
              senderNickName: state.senderNickName,
            })
          );

          state.stomp.subscribe("/sub/memorandum/join/" + state.roomCode, (res) => {
            var content = JSON.parse(res.body);
            console.log(content);
          });
          state.stomp.subscribe("/sub/memorandum/action/" + state.roomCode, (res) => {
            var content = JSON.parse(res.body);
          });

          state.stomp.subscribe("/sub/chat/message/" + state.roomCode, (res) => {
            var content = JSON.parse(res.body);

            commit("receiveChatmessages", { sender: content.sender, message: content.message });
          });
        },
        (error) => {
          // 소켓 연결 실패
          // 소켓 연결 끊김
          console.log("소켓 연결 실패", error);
        }
      );
    },
    stompDisconnect({commit,state}){
      state.stomp.disconnect();
      console.log("disconnect")
      commit("setStomp", state.stomp);
    }
  },
};

export default socketStore;
