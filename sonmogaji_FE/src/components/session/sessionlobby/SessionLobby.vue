<template>
  <b-container>
    <b-row>
      <b-col>
        <b-row>
          <b-jumbotron bg-variant="info" text-variant="white" border-variant="dark">
            <template #header>환영합니다!</template>

            <template #lead>
              화면에 내 별명이 보이나요?<br />모두 참여할 때 까지 기다려주세요.
            </template>

            <hr class="my-4" />
          </b-jumbotron>
        </b-row>
        <b-row>
          <b-jumbotron bg-variant="info" text-variant="white" border-variant="dark">
            <template #header>방 코드<br />링크 복사</template>
          </b-jumbotron>
        </b-row>
      </b-col>
      <b-col>
        <b-jumbotron bg-variant="info" text-variant="white" border-variant="dark">
          <b-button variant="success" @click="start()">시작</b-button>
        </b-jumbotron>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapState,mapActions} from "vuex"
export default {
  name: "SessionLobby",
  components: {},
  created() {
    
    
  },
  mounted(){
    console.log("sessionLobby");
    this.stompConnect();
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["stompConnect"]),
    start() {
      console.log("시작");
      this.$router.push({ name: "sessionMain1" });
    },
  },
};
</script>
