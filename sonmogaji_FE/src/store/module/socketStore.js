// import Stomp from "webstomp-client";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const socketStore = {
  state: {
    roomId: "",
    senderNickName: "",
    roll: "",
    socket: {},
    stomp: {},
    chatmessages: [],
    memorandumState: {},
  },

  getters: {
    getRoomId(state) {
      return state.stomp;
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
    setRoomId(state, roomId) {
      state.roomId = roomId;
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
    enterRoomId({ commit, state }, roomId) {
      commit("setRoomId", roomId);
    },
    roomCreate({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/create",
        {},
        JSON.stringify({
          roomId: state.roomId,
        })
      );
    },

    // roomJoin({ commit, state },roomId) {
    //   state.stomp.send(
    //     "/pub/memorandum/join",
    //     {},
    //     JSON.stringify({
    //       roomId: roomId,
    //       senderNickname:state.nickname
    //     })
    //   );
    //   commit("setRoomId", roomId)
    // },
    roomStart({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/start",
        {},
        JSON.stringify({
          roomId: state.roomId,
        })
      );
    },
    roomVote({ commit, state }, payload) {
      state.stomp.send(
        "/pub/memorandum/action",
        {},
        JSON.stringify({
          roomId: state.roomId,
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
          roomId: state.roomId,
        })
      );
    },
    clearChat({ commit }) {
      commit("setChatmessages", []);
    },

    stompConnect({ commit, state }) {
      const serverURL = "https://j7a308.p.ssafy.io:8080/room";

      // let socket = new SockJS(serverURL);
      commit("setSocket", new SockJS(serverURL));
      console.log(state.socket);
      // let stompClient = Stomp.over(state.socket);
      console.log("param nick : " + state.senderNickName);

      commit("setStomp", Stomp.over(state.socket));
      console.log(state.stomp);
      state.stomp.connect(
        {},
        () => {
          // 소켓 연결 성공
          state.stomp.connected = true;

          commit("setStomp", state.stomp);
          //commit("setSenderNickName", state.senderNickName);
          //commit("setRoomId", roomId);
          commit("setChatmessages", [{}, {}]);
          console.log(state.chatmessages);
          console.log("소켓 연결 성공" + state.senderNickName + " " + state.roomId);
          state.stomp.send(
            "/pub/memorandum/join",
            {},
            JSON.stringify({
              roomId: state.roomId,
              senderNickName: state.senderNickName,
            })
          );

          state.stomp.subscribe("/sub/memorandum/join/" + state.roomId, (res) => {
            var content = JSON.parse(res.body);
            console.log(content)
            //commit("setRoomId",content.roomId)
            console.log(content.chatLog);
            commit("setChatmessages", content.chatLog);
          });
          state.stomp.subscribe("/sub/memorandum/action/" + state.roomId, (res) => {
            var content = JSON.parse(res.body);
          });

          state.stomp.subscribe("/sub/chat/message/" + state.roomId, (res) => {
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
