// import Stomp from "webstomp-client";
import Stomp from "stompjs";
import SockJS from "sockjs-client";
import routes from "../../router/index.js";
const socketStore = {
  state: {
    roomCode: "",
    senderNickName: "",
    roll: "",
    socket: {},
    stomp: {},
    chatmessages: [],
    agree1:0,
    agree2:0,
    agree3:0,
    agree4:0,
    agree5:0,

    sign: [],
    memorandumState: {
      agree: [],
      title: "",
      content: "",

      secret: true,
      expire: "",
      memoryImage: "",
      memorySecret: true,
      sign: [],
    },
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
    getAgree1(state) {
      return state.agree1;
    },
    getAgree2(state) {
      return state.agree2;
    },
    getAgree3(state) {
      return state.agree3;
    },
    getAgree4(state) {
      return state.agree4;
    },
    getAgree5(state) {
      return state.agree5;
    },

    getSign(state) {
      return state.sign;
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
    setAgree1(state, count) {
      state.agree1 = count;
    },
    setAgree2(state, count) {
      state.agree2 = count;
    },
    setAgree3(state, count) {
      state.agree3 = count;
    },
    setAgree4(state, count) {
      state.agree4 = count;
    },
    setAgree5(state, count) {
      state.agree5 = count;
    },
    setSign(state, sign) {
      state.sign = sign;
    },
    setMemorandumState(state, memorandumstate) {
      state.memorandumState = memorandumstate;
    },
  },

  actions: {
    enterNickName({ commit, state }, senderNickName) {
      commit("setSenderNickName", senderNickName);
    },
    enterRoll({ commit, state }, roll) {
      commit("setRoll", roll);
    },
    enterRoomCode({ commit, state }, roomCode) {
      commit("setRoomCode", roomCode);
    },
    changeAgree1({ commit, state }){
      let count=0;
      Object.entries(state.memorandumState.agree[0]).forEach(([key, value]) => {
        if(value){
          count++;
        }
      });
      commit("setAgree1", count);
    },
    changeAgree2({ commit, state }){
      let count=0;
      Object.entries(state.memorandumState.agree[1]).forEach(([key, value]) => {
        if(value){
          count++;
        }
      });
      commit("setAgree2", count);
    },
    changeAgree3({ commit, state }){
      let count=0;
      Object.entries(state.memorandumState.agree[2]).forEach(([key, value]) => {
        if(value){
          count++;
        }
      });
      commit("setAgree3", count);
    },
    changeAgree4({ commit, state }){
      let count=0;
      Object.entries(state.memorandumState.agree[3]).forEach(([key, value]) => {
        if(value){
          count++;
        }
      });
      commit("setAgree4", count);
    },
    changeAgree5({ commit, state }){
      let count=0;
      Object.entries(state.memorandumState.agree[4]).forEach(([key, value]) => {
        if(value){
          count++;
        }
      });
      commit("setAgree5", count);
    },
    changeSign({ commit, state }){
      let signstate=[];
      Object.entries(state.memorandumState.sign).forEach(([key, value]) => {
        // do something with key and val
        if(value!=""){
          let t=new Object();
          t.nickname=key;
          t.state=true;
          signstate.push(t);
          
        }

      });

      commit("setSign", signstate);
    },

    roomStart({ commit, state }) {
      state.stomp.send(
        "/pub/memorandum/start",
        {},
        JSON.stringify({
          roomCode: state.roomCode,
        })
      );
    },
    roomVote({ commit, state }, index) {
      state.stomp.send(
        "/pub/memorandum/vote",
        {},
        JSON.stringify({
          roomCode: state.roomCode,
          message: index-1,
        })
      );
    },
    roomVoteCancel({ commit, state }, index) {
      state.stomp.send(
        "/pub/memorandum/votecancel",
        {},
        JSON.stringify({
          roomCode: state.roomCode,
          message: index-1,
        })
      );
    },
    roomNext({ commit, state }, index) {
      state.stomp.send(
        "/pub/memorandum/next",
        {},
        JSON.stringify({
          roomCode: state.roomCode,
          message: index-1,
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

    stompConnect({ commit, state,dispatch }) {
      // const serverURL = "https://j7a308.p.ssafy.io/room";
      const serverURL = "/room";
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
            if (content.message != "ok") {
              dispatch("stompDisconnect")
              // this.stompDisconnect();
            }
          });
          state.stomp.subscribe("/sub/memorandum/start/" + state.roomCode, (res) => {
            var content = JSON.parse(res.body);
            console.log(content);

            if (content.message == "start") {
              commit("setMemorandumState", content.memorandumState);
              dispatch("changeAgree1")
              dispatch("changeSign")

              routes.push({ name: "sessionMain1" });
            } else {
              console.log(content.message);
            }
          });
          state.stomp.subscribe("/sub/memorandum/vote/" + state.roomCode, (res) => {
            var content = JSON.parse(res.body);
            if (content.message == "1") {
              commit("setMemorandumState", content.memorandumState);
              dispatch("changeAgree1")
              dispatch("changeSign")
            }
            else if(content.message == "2") {
              commit("setMemorandumState", content.memorandumState);
              dispatch("changeAgree2")
              dispatch("changeSign")
            }
            else if(content.message == "3") {
              commit("setMemorandumState", content.memorandumState);
              dispatch("changeAgree3")
              dispatch("changeSign")
            }
            else if(content.message == "4") {
              commit("setMemorandumState", content.memorandumState);
              dispatch("changeAgree4")
              dispatch("changeSign")
            }
            else if(content.message == "5") {
              commit("setMemorandumState", content.memorandumState);
              dispatch("changeAgree5")
              dispatch("changeSign")
            }
            else{
              console.log(content.message)
            }
          });
          state.stomp.subscribe("/sub/memorandum/next/" + state.roomCode, (res) => {
            var content = JSON.parse(res.body);
            if (content.message == "2") {
              routes.push({ name: "sessionMain2" });
            }
            else if(content.message == "3") {
              routes.push({ name: "sessionMain3" });
            }
            else if(content.message == "4") {
              routes.push({ name: "sessionMain4" });
            }
            else if(content.message == "5") {
              routes.push({ name: "sessionMain5" });
            }
            else if(content.message == "6") {
              routes.push({ name: "sessionMain6" });
            }
            else{
              console.log(content.message)
            }
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
    stompDisconnect({ commit, state }) {
      state.stomp.disconnect();
      console.log("disconnect");
      commit("setStomp", state.stomp);
    },
  },
};

export default socketStore;
