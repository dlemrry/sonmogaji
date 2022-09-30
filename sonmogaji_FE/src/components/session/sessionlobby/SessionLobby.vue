<template>
  <b-container>
    <b-row>
      <b-col>
        <b-row>
          <div class="menu-box bg-navy shadow-lg rounded p-4">
            <div class="mb-4">
              <p class="h3">환영합니다!</p>
              <p class="fw-light">화면에 내 별명이 보이나요?</p>
              <p class="fw-light">모두 참여할 때 까지 기다려주세요.</p>
            </div>
          </div>
        </b-row>
        <b-row>
          <div class="menu-box bg-navy shadow-lg rounded p-4">
            <b-row><b-col><p class="h3">방 코드</p></b-col> <b-col>{{this.getRoomCode}}</b-col></b-row>
            <b-row><b-col><p class="h3">링크 복사</p></b-col> <b-col></b-col></b-row>

          </div>

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
import { mapState, mapActions, mapGetters } from "vuex";
export default {
  name: "SessionLobby",
  components: {},
  computed: {
    ...mapGetters(["getRoomCode"]),
  },
  created() {},
  mounted() {
    console.log("sessionLobby");
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["memorandumStart"]),
    start() {
      console.log("시작");
      this.$router.push({ name: "sessionMain1" });
    },
  },
};
</script>
