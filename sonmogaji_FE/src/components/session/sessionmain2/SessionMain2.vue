<template>
  <b-container>
    <b-row>
      <b-col cols="1">
        <b-row class="activecircledot" />
        <b-row>제목 / 내용</b-row>
      </b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /> <b-row>옵션 추가</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>이미지 등록</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>서명 추가</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>최종 확인</b-row></b-col>
    </b-row>
    <b-row>제목</b-row>
    <b-row>
      <b-form-input
        id="input-default"
        size="sm"
        placeholder="각서의 제목을 입력하세요"
      ></b-form-input>
    </b-row>
    <b-row>내용</b-row>
    <b-row>
      <b-form-textarea
        id="textarea-rows"
        rows="8"
        placeholder="약속할 내용을 입력하세요"
      ></b-form-textarea>
    </b-row>
    <b-row>
          <b-col>{{ this.getAgree2 }} / {{ Object.keys(this.getMemorandumState.agree[1]).length - 1 }}  명이 동의했습니다</b-col> </b-row>
        
    <b-row>
      <b-col><b-button @click="toMain3">진행</b-button></b-col>
      <b-col><b-button @click="vote2"> 동의</b-button></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
export default {
  name: "SessionMain2",
  components: {},
  computed:{
    ...mapState(["memorandumState", "agree2"]),
    ...mapGetters(["getMemorandumState", "getAgree2"]),
  }
  ,
  created() {
    //this.$router.push({name:"main1Slide1"})
    console.log("sessionMain2");
  },
  data() {
    return {
      agreeCount: 0,
      memberCount: 4,
    };
  },
  methods: {
    ...mapActions(["roomVote", "roomVoteCancel", "roomNext"]),
    toMain3() {
      this.roomNext(3);
    },
     vote2() {
      this.roomVote(2);
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
