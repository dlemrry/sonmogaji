<template>
  <div id="main-menu">
    <b-container>
      <div class="d-flex my-3">
        <div class="circle bg-cyan mx-1"></div>
        <div class="circle bg-light-gray mx-1"></div>
        <div class="circle bg-light-gray mx-1"></div>
      </div>
      <b-row>
        <b-col cols="9">
          <b-card id="banner" class="shadow-lg rounded p-5">
            <h1 class="display-4 fw-semibold my-5">약속은 즐거운 추억입니다!</h1>
            <p class="h2 fw-light my-3">
              손모가지는, 친구들과 실시간으로 각서를 작성하며<br />
              추억을 만들 수 있는 블록체인 기반 각서 플랫폼입니다.
            </p>
          </b-card>
        </b-col>
        <b-col cols="3">
          <b-card class="menu bg-cyan shadow-lg rounded"></b-card>
          <b-card class="menu bg-navy shadow-lg rounded"></b-card>
        </b-col>
      </b-row>
    </b-container>
    <div class="my-5 text-center">
      <div v-animate-css.hover="'bounce'" style="width: 200px" class="mx-auto" @click="scroll">
        <p class="h4 mb-3">체인 둘러보기</p>
        <img src="@/assets/down.png" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainMenu",
  methods: {
    scroll() {
      window.scrollTo({ left: 0, top: screen.height - 100, behavior: "smooth" });
      console.log("hi");
    },
  },
};
</script>

<style scoped>
#main-menu {
  height: 100vh;
}
</style>
