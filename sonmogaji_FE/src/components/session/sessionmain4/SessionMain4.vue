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
    <b-row>
      <b-col>{{ agreeCount }} / {{ memberCount }} 명이 동의했습니다</b-col>
    </b-row>

    <b-row>
      <b-col><b-button @click="toMain5">진행</b-button></b-col>
      <b-col><b-button >동의</b-button></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "SessionMain4",
  components: {},
  created() {
    //this.$router.push({name:"main1Slide1"})
    console.log("sessionMain4");
  },
  computed: {
    disabled() {
      return this.status === "disabled";
    },
  },
  data() {
    return {
      agreeCount: 0,
      memberCount: 4,
      selected: "",
      status: "",
    };
  },
  methods: {
    toMain5() {
      this.$router.push({ name: "sessionMain5" });
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
