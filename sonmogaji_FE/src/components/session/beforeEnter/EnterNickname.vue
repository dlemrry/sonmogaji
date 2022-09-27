<template>
  <b-container>
    <b-row>
      <b-col>별명을 입력하세요!</b-col> <b-col>{{ this.status }}</b-col></b-row
    >
    <b-row>
      <input v-model="nicknameinput" id="nickname" type="text" />
    </b-row>
    <b-row>
      <b-button @click="enter()">입장하기</b-button>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
export default {
  name: "EnterNickname",
  components: {},
  computed: {
    ...mapState(["senderNickName"]),
    ...mapGetters(["getSenderNickName"])
  },
  created() {},
  mounted() {
    console.log("EnterNickname");
  },
  data() {
    return {
      nicknameinput: "",
      status: "",
    };
  },
  methods: {
    ...mapActions(["enterNickName"]),
    enter() {
      if (this.nicknameinput != "") {
        this.enterNickName(this.nicknameinput)
        // this.$store.commit('setSenderNickName',this.nicknameinput)
        //this.enterNickName(this.getSenderNickName);
        this.$router.push({ name: "chooseRoll" });
      } else {
        this.status = "별명을 확인해주세요";
      }
    },
  },
};
</script>
