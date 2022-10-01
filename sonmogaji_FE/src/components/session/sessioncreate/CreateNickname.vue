<template>
  <b-container>
    <b-row>
      <b-col>별명을 입력하세요!</b-col> <b-col>{{ this.status }}</b-col></b-row
    >
    <b-row>
      <input v-model="nicknameinput" id="nickname" type="text" />
    </b-row>
    <b-row>
      <b-button @click="create()">방만들기</b-button>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import axios from "axios";
export default {
  name: "cnterNickname",
  components: {},
  computed: {
    ...mapState(["senderNickName"]),
    ...mapGetters(["getSenderNickName"]),
  },
  created() {},
  mounted() {
    console.log("cnterNickname");
  },
  data() {
    return {
      nicknameinput: "",
      status: "",
    };
  },
  methods: {
    ...mapActions(["enterNickName","enterRoomCode","enterRoll"]),
    create() {
      if (this.nicknameinput != "") {
        this.enterNickName(this.nicknameinput);
        axios
          .post("/api/room/create", {
            senderNickName: this.getSenderNickName,
          })
          .then( (response)=> {
            this.enterRoll("signee");
            console.log(response.data.roomCode);
            this.enterRoomCode(response.data.roomCode);
            
            this.$router.push({ name: "session" });
          })
          .catch( (error) =>{
            console.log(error);
          })
          .finally( () =>{
          });
      } else {
        this.status = "별명을 확인해주세요";
      }
    },
  },
};
</script>
