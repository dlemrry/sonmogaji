<template>
  <b-container>
    <b-row>
      <b-col cols="1">
        <b-row class="activecircledot" />
        <b-row>제목 / 내용</b-row>
      </b-col>
      <b-col><b-row class="activebar" /></b-col>
      <b-col cols="1"><b-row class="activecircledot" /> <b-row>옵션 추가</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>이미지 등록</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>서명 추가</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>최종 확인</b-row></b-col>
    </b-row>
    <b-row>각서 공개 여부</b-row>
    <b-row>
      <b-col
        ><b-form-radio
          v-model="selected"
          name="some-radios"
          value="A"
          >공개</b-form-radio
        ></b-col
      >
      <b-col
        ><b-form-radio
          v-model="selected"
          name="some-radios"
          value="B"
          >비공개</b-form-radio
        ></b-col
      >
    </b-row>
    <b-row>각서 만료 날짜</b-row>
    <b-row>
      <b-col
        ><b-form-datepicker id="ex-disabled-readonly" :disabled="disabled"></b-form-datepicker>
      </b-col>
      <b-col
        ><b-form-checkbox
          id="checkbox-1"
          v-model="status"
          name="checkbox-1"
          value="disabled"
          unchecked-value=""
        >
          무기한
        </b-form-checkbox></b-col
      >
    </b-row>
    <b-row>
      <b-col>{{ this.getAgree3  }} / {{ Object.keys(this.getMemorandumState.agree[2]).length - 1  }} 명이 동의했습니다</b-col>
    </b-row>

    <b-row>
      <b-col><b-button @click="toMain4">진행</b-button></b-col>
      <b-col><b-button @click="vote3">동의</b-button></b-col>
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
    ...mapState(["memorandumState", "agree3"]),
    ...mapGetters(["getMemorandumState", "getAgree3"]),
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
    toMain4() {
      this.roomNext(4);
    },
    vote3() {
      this.roomVote(3);
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
