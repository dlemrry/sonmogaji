<template>
  <b-container>
    <b-row>
      <b-col cols="1">
        <b-row class="activecircledot" />
        <b-row>제목 / 내용</b-row>
      </b-col>
      <b-col><b-row class="activebar" /></b-col>
      <b-col cols="1"><b-row class="activecircledot" /> <b-row>옵션 추가</b-row></b-col>
      <b-col><b-row class="activebar" /></b-col>
      <b-col cols="1"><b-row class="activecircledot" /><b-row>이미지 등록</b-row></b-col>
      <b-col><b-row class="activebar" /></b-col>
      <b-col cols="1"><b-row class="activecircledot" /><b-row>서명 추가</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>최종 확인</b-row></b-col>
    </b-row>
    <b-row>각서에 서명하세요!</b-row>
    <b-row>
      <b-col
        ><b-button id="show-btn" @click="$bvModal.show('bv-modal-example')">Open Modal</b-button>

        <b-modal id="bv-modal-example" hide-footer>
          <template #modal-title> Using <code>$bvModal</code> Methods </template>
          <div class="d-block text-center">
            <h3>Hello From This Modal!</h3>
          </div>
          <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-example')"
            >Close Me</b-button
          >
        </b-modal></b-col
      >
    </b-row>

    <b-row>
      <b-col
        >{{ this.getAgree5 }} / {{ Object.keys(this.getMemorandumState.agree[4]).length - 1 }} 명이
        서명했습니다</b-col
      >
    </b-row>

    <b-row>
      <b-col><b-button @click="toMain6">진행</b-button></b-col>
      <b-col><b-button @click="vote5">동의</b-button></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  name: "SessionMain3",
  components: {},
  created() {
    //this.$router.push({name:"main1Slide1"})
    console.log("sessionMain3");
  },
  computed: {
    ...mapState(["memorandumState", "agree5"]),
    ...mapGetters(["getMemorandumState", "getAgree5"]),
    disabled() {
      return this.status === "disabled";
    },
  },
  data() {
    return {
      selected: "",
      status: "",
    };
  },
  methods: {
    ...mapActions(["roomVote", "roomVoteCancel", "roomNext"]),
    toMain6() {
       this.roomNext(6);
    },
    vote5() {
      this.roomVote(5);
    },
  },
};
</script>

<style>
.activecircledot {
  margin: auto;
  width: 20px;
  height: 20px;
  background: rgb(0, 191, 216);
  border-radius: 50%;
}
.circledot {
  margin: auto;
  width: 20px;
  height: 20px;
  background: rgb(172, 172, 172);
  border-radius: 50%;
}

.activebar {
  margin: auto;
  width: 100px;
  height: 5px;
  background: rgb(0, 191, 216);
}
.bar {
  margin: auto;
  width: 100px;
  height: 5px;
  background: rgb(172, 172, 172);
}
</style>
