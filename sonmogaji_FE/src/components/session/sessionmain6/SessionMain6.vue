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
      <b-col><b-row class="activebar" /></b-col>
      <b-col cols="1"><b-row class="activecircledot" /><b-row>최종 확인</b-row></b-col>
    </b-row>
    <b-row>불만이 있다면 지금 얘기하세요!</b-row>
    <b-row>
      
    </b-row>
    
  </b-container>
</template>

<script>
export default {
  name: "SessionMain6",
  components: {},
  created() {
    
    console.log("sessionMain6");
  },
  computed: {
    
  },
  data() {
    return {
    };
  },
  methods: {
    
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
