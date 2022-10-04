<template>
  <b-container class="bv-example-row">
    <b-row> o o o </b-row>
    <b-row>
      <b-col cols="1"> </b-col>
      <b-col
        ><b-row>
          <b-col>손모가지 에서 돌이킬 수 없는 약속을 해보세요!</b-col>
        </b-row>

        <b-row>
          <b-col
            ><b-card
              title="블록체인으로 박제!"
              :img-src="require('../../../assets/icons/chain.png')"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem"
              class="mb-2"
            >
              <b-card-text>
                작성된 각서는 블록체인에 저장되어 수정, 삭제가 불가능합니다.
              </b-card-text>
            </b-card></b-col
          >
          <b-col
            ><b-card
              title="나만의 각서 이미지!"
              :img-src="require('../../../assets/icons/folder.png')"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem"
              class="mb-2"
            >
              <b-card-text>
                이미지에는 특별한 코드가 숨겨져 있어, 간편하게 인증할 수 있습니다.
              </b-card-text>
            </b-card></b-col
          >
          <b-col>
            <b-card
              title="실시간으로 함께 작성!"
              :img-src="require('../../../assets/icons/chat.png')"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem"
              class="mb-2"
            >
              <b-card-text> 각서 내용을 실시간으로 합의하고 마음껏 내용을 다투세요! </b-card-text>
            </b-card></b-col
          >
        </b-row>
      </b-col>
      <b-col cols="1">
        <img @click="toSlide2" src="../../../assets/toRightSlide.png" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "Main1Slide1",
  components: {},
  created() {
    console.log("main1slide1");
  },
  data() {
    return {
      // slide: 0,
      // sliding: null,
    };
  },
  methods: {
    toSlide2() {
      this.$router.push({ name: "main1Slide2" });
    },
    // onSlideStart(slide) {
    //   this.sliding = true;
    // },
    // onSlideEnd(slide) {
    //   this.sliding = false;
    // },
  },
};
</script>
