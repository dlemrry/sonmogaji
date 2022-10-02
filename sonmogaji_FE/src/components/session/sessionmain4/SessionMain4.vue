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
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>서명 추가</b-row></b-col>
      <b-col><b-row class="bar" /></b-col>
      <b-col cols="1"><b-row class="circledot" /><b-row>최종 확인</b-row></b-col>
    </b-row>
    <b-row>약속을 기억할 사진을 등록하세요</b-row>
    <b-row>
      <b-col
        ><b-form-file
          v-model="file1"
          :state="Boolean(file1)"
          placeholder="Choose a file or drop it here..."
          drop-placeholder="Drop file here..."
        ></b-form-file
      ></b-col>
    </b-row>
    <b-row>사진 공개 여부</b-row>
    <b-row v-if="this.getIsHost">
      <b-col
        ><b-form-radio
          v-model="selected"
          name="some-radios"
          value="A"
          @change="memorysecretchange"
          >공개</b-form-radio
        ></b-col
      >
      <b-col
        ><b-form-radio
          v-model="selected"
          name="some-radios"
          value="B"
          @change="memorysecretchange"
          >비공개</b-form-radio
        ></b-col
      >
    </b-row>
    <b-row v-else>
      <b-col
        ><b-form-radio
          v-model="selected"
          name="some-radios"
          value="A"
          @change="memorysecretchange"
          onclick="return(false);"
          >공개</b-form-radio
        ></b-col
      >
      <b-col
        ><b-form-radio
          v-model="selected"
          name="some-radios"
          value="B"
          @change="memorysecretchange"
          onclick="return(false);"
          >비공개</b-form-radio
        ></b-col
      >
    </b-row>
    <b-row>
      <b-col>{{  this.getAgree4  }} / {{ Object.keys(this.getMemorandumState.agree[3]).length - 1  }} 명이 동의했습니다</b-col>
    </b-row>

    <b-row>
      <b-col><b-button @click="toMain5">진행</b-button></b-col>
      <b-col><b-button @click="vote4">동의</b-button></b-col>
    </b-row>
  </b-container>
</template>

<script>

import { mapActions, mapGetters, mapMutations, mapState } from "vuex";
export default {
  name: "SessionMain4",
  components: {},
  created() {
    //this.$router.push({name:"main1Slide1"})
    console.log("sessionMain4");
  },
  computed: {
    ...mapState(["memorandumState", "agree4"]),
    ...mapGetters(["getMemorandumState", "getAgree4","getMemorySecret","getIsHost"]),
   
    selected: {
      get() {
        // return this.$store.state.title;
        return this.getMemorySecret;
      },
      set(value) {
        this.setMemorySecret(value);

        //this.$store.commit("updateMessage", value);
      },
    },
  },
  data() {
    return {
      status: "",
    };
  },
  methods: {
    ...mapActions(["roomVote", "roomVoteCancel", "roomNext","sendMemorySecret"]),
    ...mapMutations(["setMemorySecret"]),
    toMain5() {
      this.roomNext(5);
    },
     vote4() {
      this.roomVote(4);
    },
    memorysecretchange() {
      console.log(this.selected);
      this.sendMemorySecret()
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
