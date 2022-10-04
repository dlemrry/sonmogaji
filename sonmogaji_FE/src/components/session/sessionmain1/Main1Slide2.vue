<template>
  <b-container class="bv-example-row">
    <b-row> o o o </b-row>
    <b-row>
      <b-col cols="1"><img @click="toSlide1" src="../../../assets/toLeftSlide.png" /> </b-col>
      <b-col>
        <b-row>
          <b-col>절차는 방장이 진행합니다.</b-col>
        </b-row>
        <b-row>
          <b-col>모두가 동의해야 진행할 수 있으니 걱정 마세요!</b-col>
        </b-row>
        <b-row></b-row>
        <b-row>
          <b-card>
            <b-card-text>
              <b-col>
                <b-row>1 채팅으로 각서에 채울 내용을 협의합니다.</b-row
                ><b-row>2 방장은 협의한 내용을 각서에 적습니다.</b-row
                ><b-row>3 참여자는 작성된 내용이 만족스럽다면, 동의 버튼을 누릅니다.</b-row
                ><b-row>4 모두가 동의를 눌렀다면, 방장이 단계를 진행합니다.</b-row>
              </b-col>
            </b-card-text>
          </b-card>
        </b-row>
      </b-col>
      <b-col cols="1">
        <img @click="toSlide3" src="../../../assets/toRightSlide.png" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Main1Slide2",
  components: {},
  created() {
    console.log("main1slide2");
  },
  data() {
    return {
      // slide: 0,
      // sliding: null,
    };
  },
  methods: {
    toSlide3 ()  {
      this.$router.push({ name: "main1Slide3" });
    },
    toSlide1 ()  {
      this.$router.push({ name: "main1Slide1" });
    },
  },
};
</script>
