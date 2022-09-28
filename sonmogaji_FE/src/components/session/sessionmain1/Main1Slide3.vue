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
          <b-col>{{ agreeCount }} / {{ memberCount }} 명이 동의했습니다</b-col> </b-row>
        <b-row>
          <b-col><b-button @click="toMain2">진행</b-button></b-col>
          <b-col><b-button>동의</b-button></b-col>
        </b-row>
      </b-col>
      <b-col cols="1">
        
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Main1Slide3",
  components: {},
  created() {
    console.log("main1slide3");
  },
  data() {
    return {
      agreeCount: 0,
      memberCount: 4,
      // slide: 0,
      // sliding: null,
    };
  },
  methods: {
    toSlide2() {
      this.$router.push({ name: "main1Slide2" });
    },
     toMain2() {
      this.$router.push({ name: "sessionMain2" });
    },
  },
};
</script>
