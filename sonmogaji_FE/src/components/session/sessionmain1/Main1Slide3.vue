<template>
  <b-container class="bv-example-row">
    <b-row> o o o </b-row>
    <b-row>
      <b-col cols="1"><img @click="toSlide2" src="../../../assets/toLeftSlide.png" /> </b-col>
      <b-col>
        <b-row>
          <b-col>돌이킬 수 없는 약속을 할 준비가 됐나요?</b-col>
        </b-row>
        <b-row>
          <b-col>아래 버튼을 눌러 시작하세요.</b-col>
        </b-row>
        <b-row>
          <b-col
            >{{ this.getAgree1 }} /
            {{ Object.keys(this.getMemorandumState.agree[0]).length - 1 }} 명이 동의했습니다</b-col
          >
        </b-row>
        <b-row>
          <b-col><b-button @click="toMain2">진행</b-button></b-col>
          <b-col><b-button @click="vote1">동의</b-button></b-col>
        </b-row>
      </b-col>
      <b-col cols="1"> </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  name: "Main1Slide3",
  components: {},
  computed: {
    ...mapState(["memorandumState", "agree1"]),
    ...mapGetters(["getMemorandumState", "getAgree1"]),
  },
  created() {
    console.log("main1slide3");
  },
  data() {
    return {
      memberCount: 4,
      // slide: 0,
      // sliding: null,
    };
  },
  methods: {
    ...mapActions(["roomVote", "roomVoteCancel", "roomNext"]),
    toSlide2() {
      this.$router.push({ name: "main1Slide2" });
    },
    toMain2() {
      this.roomNext(2);
    },
    vote1() {
      this.roomVote(1);
    },
  },
};
</script>
