<template>
  <b-container>
    <b-row>
      <b-col>방 코드를 입력하세요</b-col> <b-col>{{ this.status }}</b-col></b-row
    >
    <b-row>
      <input v-model="codeinput" id="code" type="text" />
    </b-row>
    <b-row>
      <b-button @click="next()">다음</b-button>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapGetters, mapMutations } from "vuex";
export default {
  name: "RoomCode",
  components: {},
  computed: {
    ...mapState(["senderNickName"]),
    ...mapGetters(["getSenderNickName"])
  },
  created() {
    //url 에 roomcode 있으면 바로 별명페이지 route
    //6자리 숫자 roomcode
    this.clearState();

  },
  mounted() {
    console.log("RoomCode");
  },
  data() {
    return {
      codeinput: "",
      status: "",
    };
  },
  methods: {
    ...mapActions(["enterRoomCode"]),
    ...mapMutations(["clearState"]),
    next() {
      if (this.codeinput != "") {
        // console.log(this.codeinput)
        this.enterRoomCode(this.codeinput)
        

        this.$router.push({ name: "enterNickname" });
      } else {
        this.status = "방 코드를 입력해주세요";
      }
    },
  },
};
</script>
